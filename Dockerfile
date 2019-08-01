ARG VERSION=8u151

FROM openjdk:${VERSION}-jdk as BUILD

COPY . /src
WORKDIR /src
RUN ./gradlew --no-daemon shadowJar

FROM openjdk:${VERSION}-jre

COPY --from=BUILD /src/build/libs/download-sorter-0.1 /bin/runner/run.jar
WORKDIR /bin/runner

RUN mkdir -p /downloads
RUN mkdir -p /movies
RUN mkdir -p /series

CMD ["java","-jar","run.jar"]