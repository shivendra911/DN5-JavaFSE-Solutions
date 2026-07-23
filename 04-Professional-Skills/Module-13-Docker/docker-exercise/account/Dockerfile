# Module 13 - Docker Exercise
# Containerizing the 'account' microservice built in Module 8

# ---- Stage 1: Build the jar using Maven ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# download dependencies first - this layer gets cached and only re-runs
# if pom.xml changes, making rebuilds much faster
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Stage 2: Run the jar using a lightweight JRE-only image ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# copy only the built jar from Stage 1 - keeps final image small
# (no Maven, no source code, no build tools in the final image)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
