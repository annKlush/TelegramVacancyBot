
# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /app
COPY .mvn/ .mvn                         # Copying the .mvn folder from the local directory to the /app/.mvn folder inside the container
COPY mvnw pom.xml ./                    # Copying mvnw and pom.xml files from the local directory to the root folder inside the container
RUN ./mvnw dependency:resolve           # Running the command to resolve project dependencies
COPY src ./src                          # Copying the source files from the local directory to the /app/src folder inside the container

FROM development
 base asCMD ["./mvnw", "spring-boot:run"]       # Running the command to execute the Spring Boot application in development mode

FROM base as build
RUN ./mvnw package                      # Running the command to package the application

FROM eclipse-temurin:17-jre-jammy as production
EXPOSE 8080                             # Exposing port 8080 to the host machine
COPY --from=build /app/target/spring-petclinic-*.jar /spring-petclinic.jar    # Copying the packaged JAR file from the build stage to the root folder inside the container and renaming it to spring-petclinic.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-petclinic.jar"]    # Running the command to start the Spring Boot application in production mode
