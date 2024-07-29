# Estágio de construção
FROM openjdk:17-jdk-slim AS build

RUN apt-get update && \
    apt-get install -y curl && \
    echo "Downloading Maven..." && \
    curl -O https://downloads.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz && \
    tar -xzf apache-maven-3.9.5-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.9.5/bin/mvn /usr/bin/mvn

COPY . /app
WORKDIR /app

RUN mvn clean install
RUN ls -l /app/target  

# Estágio final
FROM openjdk:17-jdk-slim

COPY --from=build /app/target/todolist-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]
