FROM openjdk:16-jdk-alpine

COPY target/Itunes-0.0.1-SNAPSHOT.jar /app/Itunes.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "Itunes.jar"]