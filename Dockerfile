# BUILD STAGE
FROM gradle:8.1.1-jdk17 AS builder

ARG NEXUS_URL

WORKDIR /appdir

# COPY Gradle files
COPY ./settings.gradle ./build.gradle ./

# COPY DATA SPHERE SERVICE
COPY ./src ./src

RUN gradle build --no-build-cache --info --stacktrace --refresh-dependencies -PNEXUS_URL=$NEXUS_URL

# RUN STAGE
FROM  amazoncorretto:20-alpine3.17 AS runner

WORKDIR /myapp

# Copying main executable jar
COPY --from=builder /appdir/build/libs/*SNAPSHOT.jar ./myapp.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default", "/myapp/myapp.jar"]

EXPOSE 8086
