
# Dockerfile

# Use the specified base image
FROM eclipse-temurin:17-jdk-alpine

# Create a volume inside the container
VOLUME /tmp

# Copy the JAR file from the host machine to the container
COPY target/*.jar app.jar

# Specify the entry point command for the container to execute upon startup
ENTRYPOINT ["java","-jar","/app.jar"]
