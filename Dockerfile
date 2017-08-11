FROM debian:latest

RUN apt-get -y update && apt-get install -y openjdk-8-jre-headless openjdk-8-jdk maven git vim procps

COPY build/libs/elsie-dee-audiorip-1.0-SNAPSHOT.jar /ekholabs/elsie-dee-audiorip.jar

WORKDIR ekholabs

ENV CONFIGURATION_SERVER_HOST_NAME=configuration-service
ENV CONFIGURATION_SERVER_PORT=8082
EXPOSE $CONFIGURATION_SERVER_PORT

ENTRYPOINT ["java"]
CMD ["-server", "-Xmx1G", "-jar", "elsie-dee-audiorip.jar"]
