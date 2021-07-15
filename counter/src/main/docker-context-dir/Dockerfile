FROM openjdk:11-slim

COPY maven/target/*.jar app.jar

RUN mkdir config

CMD ["java", "-jar", "app.jar"]