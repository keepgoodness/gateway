# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven/Gradle wrapper and configuration files
COPY mvnw pom.xml /app/
COPY .mvn /app/.mvn

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the project files into the container
COPY . /app

# Build the application
RUN ./mvnw package

# Run the application
CMD ["java", "-jar", "target/gateway-service-0.0.1-SNAPSHOT.jar"]