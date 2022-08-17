FROM adoptopenjdk/openjdk11:latest
EXPOSE 8082
ARG JAR_FILE=./build/libs/subscriber1-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-jar","/app.jar"]