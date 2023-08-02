# Blueprint: Streaming API based on Java 17, Spring Boot 3 and Gradle with Nexus 3 

This project is a blueprint for a Streaming API based on Java 17, Spring Boot 3 and Gradle with optional Nexus 3 configuration.

Purpose of it is to show how to configure Nexus 3 as Gradle dependency repository.

## Article

[Nexus 3 OSS as a Maven and Gradle repository to win time or "How dev nomads work offline"
](https://medium.com/@antukhov/nexus-3-oss-as-a-maven-and-gradle-repository-to-win-time-or-how-dev-nomads-work-offline-8d775669cb23)

## Setup Nexus 3
> this step is optional, you can skip it if you don't want to use Nexus 3

### Dockerized

- Run ```docker-compose -f ./nexus3/docker-compose.ymal up -d``` to build and run Nexus 3 in Docker container, then open http://localhost:8081/ in your browser

### Without docker 

- Download and install [Nexus 3 OSS](https://www.sonatype.com/download-oss-sonatype)

## Run app

> [Define environment variable](https://en.wikipedia.org/wiki/Environment_variable) ```NEXUS_URL``` with value e.g. ```http://localhost:8081``` if you want to use Nexus 3

### IDE (e.g. IntelliJ IDEA):

- Import project as Gradle project
- Run ```com.antukhov.nexus3.MyApplication``` class

### CLI:

- Run ```./gradlew bootRun``` or ```gradle bootRun```

### [Dockerized](https://developerexperience.io/articles/dockerizing): 

> docker-compose.yaml is configured to use Nexus 3 as dependency repository and pass ```NEXUS_URL``` as argument to Docker image. Remove it if you don't want to use Nexus 3

- Run ```docker-compose up -d``` to build and run sample app in Docker container, then open http://localhost:8080/ in your browser

## API requests: 

> Request samples are in [request](./request) folder (if you use IntelliJ IDEA, you can import them as [HTTP requests](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html))

Otherwise, open link below in your browser, curl or Postman (doesn't support streaming API)

- IDEA/CLI: http://localhost:8090/my-endpoint/param/myparam 
- Dockerized: http://localhost:8080/my-endpoint/param/myparam 