FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=./build/libs/resource_service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-jar","/app.jar"]