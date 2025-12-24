<<<<<<< HEAD
🎓 LMS Core System

LMS Core System — бэкенд-платформа для управления образовательными курсами, реализованная на Spring Boot 3.2 и PostgreSQL.
Система поддерживает управление учебным контентом, регистрацию студентов через связь Many-to-Many, проверку заданий и модуль тестирования.

🛠 Технологический стек

Java 22

Spring Boot 3.2

Spring Data JPA

Spring Web

Spring Validation

PostgreSQL 15 — основная база данных

Docker & Docker Compose — контейнеризация и окружение

Swagger (SpringDoc) — интерактивная документация API

Lombok — сокращение шаблонного кода

🚀 Инструкция по запуску
🔹 Вариант 1: Docker (рекомендуется)

1. Сборка проекта

mvn clean package -DskipTests


2. Запуск инфраструктуры

docker-compose up --build -d


📍 Приложение будет доступно по адресу:
http://localhost:8081

Проверка статуса контейнеров

docker-compose ps

🔹 Вариант 2: Локальный запуск
1. База данных

Создайте базу данных в PostgreSQL:

CREATE DATABASE lms_db;

2. Конфигурация

src/main/resources/application.properties
=======
# LMS Core System

Backend-платформа для управления образовательными курсами на Spring Boot 3.2 и PostgreSQL.  
Проект реализует управление контентом, регистрацию студентов через связь Many-to-Many, систему заданий и модуль тестирования.

## Технологический стек

- Java 22
- Spring Boot 3.2
   - Spring Data JPA
   - Spring Web
   - Spring Validation
- PostgreSQL 15
- Docker, Docker Compose
- Swagger (SpringDoc)
- Lombok

## Запуск проекта

### Docker (рекомендуется)

- Сборка проекта:  
  `mvn clean package -DskipTests`

- Запуск контейнеров:  
  `docker-compose up --build -d`

- Приложение доступно на порту **8081**

- Проверка статуса:  
  `docker-compose ps`

### Локальный запуск

- Создание базы данных:  
  `CREATE DATABASE lms_db;`

- Конфигурация:  
  `src/main/resources/application.properties`

  Параметры подключения:
   - `server.port=8081`
   - `spring.datasource.url=jdbc:postgresql://localhost:5433/lms_db`
   - `spring.datasource.username=postgres`
   - `spring.datasource.password=1151`
>>>>>>> d46cd73 (chore: readme)

- Запуск приложения:  
  `mvn spring-boot:run`

<<<<<<< HEAD
spring.datasource.url=jdbc:postgresql://localhost:5433/lms_db
spring.datasource.username=postgres
spring.datasource.password=1151

3. Запуск приложения
mvn spring-boot:run

🏗 Функциональные возможности
📚 Управление обучением (Many-to-Many)

Реализована полноценная связь пользователи ↔ курсы

Студенты могут:

записываться на курсы

отслеживать активные подписки

Автоматическая генерация связующих таблиц через Hibernate

📝 Система заданий (Assignments)

Отправка решений
Студенты загружают ссылки на выполненные работы

Контроль попыток
Повторная отправка одного и того же задания блокируется

Оценивание
Преподаватель может выставлять баллы за решения

🧠 Тесты и викторины (Quizzes)

Иерархия данных:

Quiz → Questions → Answer Options


Сохранение результатов:

итоговый балл

время завершения теста

Сущность QuizResult фиксирует прогресс прохождения

🧪 Тестирование и валидация
🔬 Автоматические тесты (LmsLogicTest.java)

Cascade Delete
Проверка удаления модулей при удалении курса

Lazy Loading
Тестирование доступа к ленивым данным вне сессии

Integration Tests
Проверка записи студентов на курсы напрямую в БД

✅ Валидация API

Swagger UI используется для тестирования API

При некорректных данных (например, пустой заголовок):

возвращается 400 Bad Request

подтверждается корректная работа jakarta.validation

📸 Результаты работы

Скриншоты находятся в папке /screenshots

🧪 test.png — успешное прохождение Unit-тестов

🔗 cascade_end.png — Hibernate логи каскадных операций

⚠️ validation_test.png — 400 Bad Request в Swagger

📊 test_percent.png — отчет о покрытии кода тестами

🐳 docker_success.png — контейнеры в статусе UP
=======
## Функциональность

### Курсы и пользователи

- Реализована связь Many-to-Many между пользователями и курсами
- Студенты могут записываться на курсы и отслеживать подписки
- Связующие таблицы создаются автоматически через Hibernate

### Задания (Assignments)

- Студенты отправляют решения в виде ссылок
- Повторная отправка одного задания запрещена
- Преподаватель может выставлять оценки

### Тесты и викторины (Quizzes)

- Структура данных:
   - Quiz
   - Question
   - AnswerOption
- Результаты прохождения сохраняются в QuizResult
- Фиксируется итоговый балл и время завершения

## Тестирование

- Cascade Delete — удаление связанных сущностей при удалении курса
- Lazy Loading — проверка доступа к данным вне Hibernate-сессии
- Integration Tests — запись студентов на курсы напрямую через БД

## Валидация API

- Проверка API через Swagger UI
- При некорректных данных возвращается 400 Bad Request
- Используется jakarta.validation

## Скриншоты

Папка `/screenshots`:

- `test.png` — успешное выполнение тестов
- `cascade_end.png` — логи каскадных операций Hibernate
- `validation_test.png` — 400 Bad Request в Swagger
- `test_percent.png` — покрытие тестами
- `docker_success.png` — контейнеры в статусе UP
>>>>>>> d46cd73 (chore: readme)
