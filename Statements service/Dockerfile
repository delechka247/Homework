FROM maven:3.6.3-jdk-8-slim AS build
COPY src /home/application/src
COPY pom.xml /home/application
USER root
RUN --mount=type=cache,target=/.m2 mvn -f /home/application/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/application/target/statements-service-0.0.1-SNAPSHOT.jar /usr/local/lib/statements-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/statements-service-0.0.1-SNAPSHOT.jar"]
