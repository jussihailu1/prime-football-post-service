#Build

#FROM openjdk:18-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:18-jdk-alpine
COPY /build/libs/post-service-0.0.1-SNAPSHOT.jar post-service.jar
CMD ["java","-jar","post-service.jar"]

