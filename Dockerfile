FROM openjdk:17-jdk-slim

WORKDIR /project2

COPY /build/libs/*.jar project2.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar",  "project2.jar"]