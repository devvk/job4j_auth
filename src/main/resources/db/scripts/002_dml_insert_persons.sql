--liquibase formatted sql

--changeset devvk:002_dml_insert_persons
INSERT INTO persons (login, password) VALUES ('parsentev', '123');
INSERT INTO persons (login, password) VALUES ('ban', '123');
INSERT INTO persons (login, password) VALUES ('ivan', '123');

--rollback DELETE FROM persons WHERE login IN ('parsentev', 'ban', 'ivan');