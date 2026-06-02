# Job4j Auth

REST-сервис для управления пользователями.

## Технологии

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## Возможности

- Получение списка пользователей
- Получение пользователя по id
- Создание пользователя
- Обновление пользователя
- Удаление пользователя

## REST API

### Получить всех пользователей

```http
GET /persons
```

### Получить пользователя по id

```http
GET /persons/{id}
```

### Создать пользователя

```http
POST /persons
```

### Обновить пользователя

```http
PUT /persons/{id}
```

### Удалить пользователя

```http
DELETE /persons/{id}
```

## Запуск

```bash
./mvnw spring-boot:run
```
