FROM openjdk:17

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# docker build -t apigateway-dockerfile .