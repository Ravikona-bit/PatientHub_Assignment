FROM openjdk:17-jdk-alpine
COPY target/patient-hub-service.jar patient-hub-service.jar
EXPOSE 8080
CMD ["java", "-jar", "patient-hub-service.jar"]