FROM registry.access.redhat.com/ubi8/ubi-minimal

# https://github.com/sonatype/docker-nexus3/blob/main/Dockerfile

ARG NEXUS_VERSION=3.56.0-01
ARG NEXUS_DOWNLOAD_URL=https://download.sonatype.com/nexus/3/nexus-${NEXUS_VERSION}-unix.tar.gz
ARG NEXUS_DOWNLOAD_SHA256_HASH=72224300df72eae532c8ada918037314b3c4af0bed26b4e25b03dfa4227934ff

# configure nexus runtime
ENV SONATYPE_DIR=/opt/sonatype
ENV NEXUS_HOME=${SONATYPE_DIR}/nexus \
    NEXUS_DATA=/nexus-data \
    NEXUS_CONTEXT='' \
    SONATYPE_WORK=${SONATYPE_DIR}/sonatype-work \
    DOCKER_TYPE='rh-docker'

# Install Java & tar
RUN microdnf update -y \
    && microdnf --setopt=install_weak_deps=0 --setopt=tsflags=nodocs install -y \
          java-1.8.0-openjdk-headless tar procps shadow-utils gzip \
    && microdnf clean all \
    && groupadd --gid 200 -r nexus \
    && useradd --uid 200 -r nexus -g nexus -s /bin/false -d /opt/sonatype/nexus -c 'Nexus Repository Manager user'

WORKDIR ${SONATYPE_DIR}

# Download nexus & setup directories
RUN curl -L ${NEXUS_DOWNLOAD_URL} --output nexus-${NEXUS_VERSION}-unix.tar.gz \
    && echo "${NEXUS_DOWNLOAD_SHA256_HASH} nexus-${NEXUS_VERSION}-unix.tar.gz" > nexus-${NEXUS_VERSION}-unix.tar.gz.sha256 \
    && sha256sum -c nexus-${NEXUS_VERSION}-unix.tar.gz.sha256 \
    && tar -xvf nexus-${NEXUS_VERSION}-unix.tar.gz \
    && rm -f nexus-${NEXUS_VERSION}-unix.tar.gz nexus-${NEXUS_VERSION}-unix.tar.gz.sha256 \
    && mv nexus-${NEXUS_VERSION} $NEXUS_HOME \
    && chown -R nexus:nexus ${SONATYPE_WORK} \
    && mv ${SONATYPE_WORK}/nexus3 ${NEXUS_DATA} \
    && ln -s ${NEXUS_DATA} ${SONATYPE_WORK}/nexus3

# Removing java memory settings from nexus.vmoptions since now we use INSTALL4J_ADD_VM_PARAMS
RUN sed -i '/^-Xms/d;/^-Xmx/d;/^-XX:MaxDirectMemorySize/d' $NEXUS_HOME/bin/nexus.vmoptions

RUN echo "#!/bin/bash" >> ${SONATYPE_DIR}/start-nexus-repository-manager.sh \
   && echo "cd /opt/sonatype/nexus" >> ${SONATYPE_DIR}/start-nexus-repository-manager.sh \
   && echo "exec ./bin/nexus run" >> ${SONATYPE_DIR}/start-nexus-repository-manager.sh \
   && chmod a+x ${SONATYPE_DIR}/start-nexus-repository-manager.sh \
   && sed -e '/^nexus-context/ s:$:${NEXUS_CONTEXT}:' -i ${NEXUS_HOME}/etc/nexus-default.properties

RUN microdnf remove -y gzip shadow-utils

VOLUME ${NEXUS_DATA}

EXPOSE 8081
USER nexus

ENV INSTALL4J_ADD_VM_PARAMS="-Xms2703m -Xmx2703m -XX:MaxDirectMemorySize=2703m -Djava.util.prefs.userRoot=${NEXUS_DATA}/javaprefs"

CMD ["/opt/sonatype/nexus/bin/nexus", "run"]