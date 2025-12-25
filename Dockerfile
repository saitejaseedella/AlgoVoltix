# Use a reliable OpenJDK 21 base image
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file into the container
COPY voltix-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
