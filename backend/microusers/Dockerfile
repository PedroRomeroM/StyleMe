FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/microusers-0.0.1-SNAPSHOT.jar microusers.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "microusers.jar"]
