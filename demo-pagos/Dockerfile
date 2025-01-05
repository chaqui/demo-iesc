FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/demo-pagos-0.0.1-SNAPSHOT.jar demo-pagos.jar
EXPOSE 8080
CMD ["java", "-jar", "demo-pagos.jar"]