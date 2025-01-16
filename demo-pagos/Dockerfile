# Etapa 1: Construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa 2: Imagen final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/demo-pagos-0.0.1-SNAPSHOT.jar demo-pagos.jar
EXPOSE 8080
CMD ["java", "-jar", "demo-pagos.jar"]