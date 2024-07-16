FROM openjdk:17

COPY . /app

RUN ./mvnw clean package

RUN mv target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]