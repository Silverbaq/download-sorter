ARG VERSION=8u151

FROM openjdk:${VERSION}-jdk as BUILD

COPY . /src
WORKDIR /src
RUN ./gradlew build --no-daemon shadowJar

FROM openjdk:${VERSION}-jre

COPY --from=BUILD /src/build/libs/download-sorter-0.1-all.jar download-sorter.jar
WORKDIR /

RUN mkdir -p /downloads
RUN mkdir -p /movies
RUN mkdir -p /series

CMD ["java","-jar","download-sorter.jar"]