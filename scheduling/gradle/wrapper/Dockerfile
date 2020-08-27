FROM openjdk:8-jre
MAINTAINER  Rafael Cabral <rafael@rbcabrals.me>
ENTRYPOINT ["java", \
           "-jar", "/app/scheduling-1.0.0.jar"]
WORKDIR /app
EXPOSE 8080
COPY scheduling-0.0.1-SNAPSHOT.jar  /app/scheduling-1.0.0.jar
