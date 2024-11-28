FROM maven:3.8.6-amazoncorretto-17 AS build
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src
RUN mvn package -DskipTests

FROM openjdk:17-alpine
ARG JAR_FILE=/build/target/*.jar
COPY --from=build $JAR_FILE /opt/romashkaco/romashka.jar
ENTRYPOINT ["java", "-jar", "/opt/romashkaco/romashka.jar"]