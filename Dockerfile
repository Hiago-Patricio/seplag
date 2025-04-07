FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

ARG JAR_FILE=build/libs/gestao-servidores-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
EXPOSE 5005

ENV JAVA_DEBUG_OPTS=""

# Define debug options só se for perfil dev
ENTRYPOINT ["sh", "-c", "if [ \"$SPRING_PROFILES_ACTIVE\" = \"dev\" ]; then \
  echo 'Starting in DEV mode with JDWP...'; \
  java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar app.jar; \
  else \
  echo 'Starting in non-dev mode...'; \
  java -jar app.jar; \
fi"]
