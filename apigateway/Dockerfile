FROM openjdk:11-slim

COPY target/*.jar app.jar

RUN mkdir config

CMD ["java", "-jar", "app.jar"]