# copied from claude

FROM openjdk:17-jdk-slim

WORKDIR /project2

COPY demo/build/libs/*.jar project2.jar

# Heroku uses PORT environment variable which we need to respect
ENV PORT=8080

# Expose the port that the application will run on
EXPOSE ${PORT}

# Run the application with the PORT environment variable
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar project2.jar"]