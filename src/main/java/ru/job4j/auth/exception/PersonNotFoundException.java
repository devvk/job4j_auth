package ru.job4j.auth.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(int id) {
        super("Person with id=%d not found".formatted(id));
    }
}
