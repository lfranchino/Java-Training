FROM openjdk:17
COPY build/libs/*.jar matchingapp.jar
EXPOSE 8080
CMD ["java", "-jar", "matchingapp.jar"]