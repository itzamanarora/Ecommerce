FROM eclipse-temurin:17-jre-jammy

LABEL authors="Aman"

WORKDIR /app

COPY target/Ecommerce-0.0.1-SNAPSHOT.jar Ecommerce-0.0.1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Ecommerce-0.0.1.jar"]