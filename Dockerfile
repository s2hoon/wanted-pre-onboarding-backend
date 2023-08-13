## Dockerfile
FROM openjdk:19-alpine
EXPOSE 8080
ARG JAR_FILE=/build/libs/preonboard-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java","-jar","/app.jar"]