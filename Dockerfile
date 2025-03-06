FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .
RUN demo/gradlew build --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /project2

COPY demo/build/libs/*.jar project2.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "project2.jar"]