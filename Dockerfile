# Build Steps
FROM maven:3.5.2-jdk-8-alpine AS builder

# Setup Environment
RUN mkdir -p /build
WORKDIR /build

# copy source
COPY pom.xml /build

# Install Dependencies
RUN mvn -B dependency:go-offline

# Build App
COPY src /build/src
RUN mvn clean package -DskipTests


# Package and image execution Steps #
FROM openjdk:8u171-jre-alpine AS runtime

# Setup Environment
RUN mkdir -p /app
WORKDIR /app

# Copy Build
COPY --from=builder /build/target/fare-calculation-service-1.0.jar app.jar

# Run App
# Commented to control execution from docerk-compose.yaml
#ENTRYPOINT ["java", "-Xms512m", "-Xmx4g", "-jar", "app.jar"]
