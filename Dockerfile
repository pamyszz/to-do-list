
FROM ubuntu:latest AS build


RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven


COPY . /app


WORKDIR /app


RUN mvn clean install


FROM openjdk:17-jdk-slim


EXPOSE 8080


COPY --from=build /app/target/todolist-1.0.0.jar /app/app.jar


ENTRYPOINT ["java", "-jar", "/app/app.jar"]
