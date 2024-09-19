# syntax=docker/dockerfile:1
FROM openjdk:11-jre

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

