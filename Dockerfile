FROM openjdk:17-jdk-slim

WORKDIR /project2

# First copy the build files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy source code
COPY src ./src

# Build the application inside the container
RUN ./gradlew bootJar

# Copy the JAR to the working directory
COPY build/libs/*.jar project2.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "project2.jar"]