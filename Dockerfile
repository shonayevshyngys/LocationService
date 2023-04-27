FROM openjdk:17-alpine
COPY target/location-service-0.0.1-SNAPSHOT.jar location-service-0.0.1-SNAPSHOT.jar
EXPOSE 8000
CMD ["java", "-jar","/location-service-0.0.1-SNAPSHOT.jar"]
