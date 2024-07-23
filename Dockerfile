FROM maven:3.9.8-eclipse-temurin-17 AS builder
COPY src /app/src
COPY pom.xml /app/
WORKDIR /app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17.0.11_9-jre-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]