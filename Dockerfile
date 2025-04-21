FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN ./gradlew build

CMD ["java", "-jar", "build/libs/kwiktips-0.0.1-SNAPSHOT.jar"]
