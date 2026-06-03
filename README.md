# Job4j Auth

REST-сервис для управления пользователями.

## Технологии

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Liquibase
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

## Примеры curl-запросов

### Получить всех пользователей

```bash
curl -i http://localhost:8080/persons
```

### Получить пользователя по id

```bash
curl -i http://localhost:8080/persons/1
```

### Создать пользователя

```bash
curl -i \
  -H 'Content-Type: application/json' \
  -X POST \
  -d '{"login":"test","password":"123"}' \
  http://localhost:8080/persons
```

### Обновить пользователя

```bash
curl -i \
  -H 'Content-Type: application/json' \
  -X PUT \
  -d '{"login":"test-updated","password":"456"}' \
  http://localhost:8080/persons/1
```

### Удалить пользователя

```bash
curl -i -X DELETE http://localhost:8080/persons/1
```
