FROM openjdk:22-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY target/microorquestrador-0.0.1-SNAPSHOT.jar microorquestrador.jar

ENTRYPOINT ["java", "-jar", "microorquestrador.jar"]