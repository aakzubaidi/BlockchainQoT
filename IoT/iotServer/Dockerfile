FROM openjdk:17-ea-11-jdk-slim
MAINTAINER Ali Alzubaidi

RUN apt update && apt install -y curl

RUN curl -L https://github.com/aakzubaidi/IoTserver/raw/main/iot-server/build/libs/iot-server-0.0.1-SNAPSHOT.jar > server.jar

EXPOSE 8080
ENTRYPOINT  ["java","-jar","server.jar"]
