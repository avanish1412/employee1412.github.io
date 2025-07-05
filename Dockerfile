
# Use a JDK base image
FROM openjdk:21-jdk-slim

# Add a volume to store logs or other runtime data if needed
WORKDIR /app
# Set environment variables (optional)
ENV JAVA_OPTS=""

# Add the application JAR file
COPY target/Employee-0.0.1-SNAPSHOT.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
