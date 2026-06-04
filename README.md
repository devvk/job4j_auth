# Job4j Auth

REST-сервис для управления пользователями с JWT-аутентификацией и авторизацией.

## Технологии

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Security
- Spring Data JPA
- PostgreSQL
- Liquibase
- JWT
- Maven
- Lombok

## Возможности

- Регистрация пользователя
- Получение JWT-токена через `/login`
- Защита REST API через JWT
- Получение списка пользователей
- Получение пользователя по id
- Создание пользователя
- Обновление пользователя
- Удаление пользователя

## Как работает авторизация

### Регистрация

```http
POST /persons/sign-up
```

Пароль хешируется через BCrypt и сохраняется в БД в виде хеша.

### Логин

```http
POST /login
```

Spring Security проверяет логин и пароль.
Если данные верны, сервер возвращает JWT в заголовке:

```http
Authorization: Bearer <token>
```

### Доступ к защищённым endpoint

Для всех защищённых запросов клиент должен передавать JWT:

```http
Authorization: Bearer <token>
```

JWTAuthorizationFilter проверяет:
- подпись токена;
- срок действия токена;
- целостность токена.

После проверки пользователь помещается в SecurityContext текущего запроса.

## REST API

### Регистрация пользователя

```http
POST /persons/sign-up
```

### Логин

```http
POST /login
```

### Получить всех пользователей

```http
GET /persons
```

Требует JWT.

### Получить пользователя по id

```http
GET /persons/{id}
```

Требует JWT.

### Создать пользователя

```http
POST /persons
```

Требует JWT.

### Обновить пользователя

```http
PUT /persons/{id}
```

Требует JWT.

### Удалить пользователя

```http
DELETE /persons/{id}
```

Требует JWT.

## Запуск

```bash
./mvnw spring-boot:run
```

## Примеры curl-запросов

### Регистрация

```bash
curl -i -X POST http://localhost:8080/persons/sign-up \
-H "Content-Type: application/json" \
-d '{"login":"test","password":"123"}'
```

### Логин

```bash
curl -i -X POST http://localhost:8080/login \
-H "Content-Type: application/json" \
-d '{"login":"test","password":"123"}'
```

Скопируйте JWT из заголовка Authorization.

### Получить всех пользователей

```bash
curl -i http://localhost:8080/persons \
-H "Authorization: Bearer <token>"
```

### Получить пользователя по id

```bash
curl -i http://localhost:8080/persons/1 \
-H "Authorization: Bearer <token>"
```

### Обновить пользователя

```bash
curl -i -X PUT http://localhost:8080/persons/1 \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <token>" \
-d '{"login":"test-updated","password":"456"}'
```

### Удалить пользователя

```bash
curl -i -X DELETE http://localhost:8080/persons/1 \
-H "Authorization: Bearer <token>"
```
