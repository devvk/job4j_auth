--liquibase formatted sql

--changeset devvk:001_ddl_create_persons_table
CREATE TABLE persons
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

--rollback DROP TABLE persons;
