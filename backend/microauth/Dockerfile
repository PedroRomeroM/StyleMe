FROM openjdk:22-jdk-slim

EXPOSE 8082

WORKDIR /app

COPY target/microauth-0.0.1-SNAPSHOT.jar microauth.jar

ENTRYPOINT ["java", "-jar", "microauth.jar"]