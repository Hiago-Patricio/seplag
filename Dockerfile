# Stage 1: Build the project using Gradle
FROM gradle:8.5.0-jdk21-alpine AS build
WORKDIR /app

# Copy the entire project to the container
COPY . .

# Build the application
RUN gradle clean build --no-daemon

# Stage 2: Run the application using a lightweight JDK image
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose application and debug ports
EXPOSE 8080
EXPOSE 5005

# Optional: environment variable for debug options
ENV JAVA_DEBUG_OPTS=""

# Run the application; if profile is 'dev', start with debug enabled
ENTRYPOINT ["sh", "-c", "if [ \"$SPRING_PROFILES_ACTIVE\" = \"dev\" ]; then \
  echo 'Starting in DEV mode with JDWP...'; \
  java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar app.jar; \
  else \
  echo 'Starting in non-dev mode...'; \
  java -jar app.jar; \
fi"]
