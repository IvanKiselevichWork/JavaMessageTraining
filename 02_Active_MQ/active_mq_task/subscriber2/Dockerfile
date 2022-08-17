FROM adoptopenjdk/openjdk11:latest
EXPOSE 8083
ARG JAR_FILE=./build/libs/subscriber2-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-jar","/app.jar"]