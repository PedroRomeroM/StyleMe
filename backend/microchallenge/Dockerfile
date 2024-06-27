FROM openjdk:22-jdk-slim

EXPOSE 8083

WORKDIR /app

COPY target/microchallenge-0.0.1-SNAPSHOT.jar microchallenge.jar

ENTRYPOINT ["java", "-jar", "microchallenge.jar"]