# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw  # Grant execute permission to mvnw
RUN ./mvnw dependency:resolve
COPY src src

FROM base as development
CMD ["./mvnw", "spring-boot:run"]

FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy as production
EXPOSE 8080
COPY --from=build /app/target/telegram-bot-0.0.1-SNAPSHOT.jar /SNAPSHOT.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/SNAPSHOT.jar"]
