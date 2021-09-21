FROM openjdk:16-jdk-alpine

COPY target/classes /app

WORKDIR /app

ENTRYPOINT ["java", "no.noroff.Itunes.ItunesApplication"]