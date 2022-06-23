FROM openjdk:17

RUN mkdir "app"

COPY build/libs/api-0.0.1-SNAPSHOT.jar /app

WORKDIR /app

ENTRYPOINT ["java","-jar","api-0.0.1-SNAPSHOT.jar"]


