# Use a base image with Maven and OpenJDK 17 pre-installed
FROM maven:3.8.3-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Create a new stage for the application runtime
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /app/target/*.jar ./app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
