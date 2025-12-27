# Используем легкий образ Java 22
FROM openjdk:22-jdk-slim

# Папка приложения внутри контейнера
WORKDIR /app

# Копируем скомпилированный jar-файл (он появится после mvn package)
COPY target/*.jar app.jar

# Порт, который мы видели в твоих логах
EXPOSE 8081

# Запуск
ENTRYPOINT ["java", "-jar", "app.jar"]