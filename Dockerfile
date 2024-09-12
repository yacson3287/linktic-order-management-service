FROM openjdk:21-jdk

WORKDIR /app


COPY target/icube01-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=dev

EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]