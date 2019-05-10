FROM openjdk:8
ADD target/crawler-lite-api.jar crawler-lite-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "crawler-lite-api.jar"]