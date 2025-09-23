# Etapa 1: build da aplicação
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem final com JDK leve
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/data /app/data
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]