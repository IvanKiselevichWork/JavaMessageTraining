FROM adoptopenjdk/openjdk11:latest
EXPOSE 8085
ARG JAR_FILE=./build/libs/queue-consumer-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-jar","/app.jar"]