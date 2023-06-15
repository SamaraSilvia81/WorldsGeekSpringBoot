FROM maven:3.9.2 AS build
COPY . .
RUN mvn clean package

FROM amazoncorretto:17
COPY --from=build /target/project-0.0.1-SNAPSHOT.jar project-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "project-0.0.1-SNAPSHOT.jar"]
