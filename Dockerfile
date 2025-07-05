
# Use a JDK base image
FROM openjdk:21-jdk-slim

# Add a volume to store logs or other runtime data if needed
VOLUME /tmp

# Set environment variables (optional)
ENV JAVA_OPTS=""

# Add the application JAR file
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} Employee-0.0.1-SNAPSHOT.jar
COPY --from=build /target/Employee-0.0.1-SNAPSHOT.jar demo.jar
# Run the JAR file
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /demo.jar"]