package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.auth.dto.PersonPatchDto;
import ru.job4j.auth.exception.LoginAlreadyExistsException;
import ru.job4j.auth.exception.PersonNotFoundException;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public Person create(Person person) {
        if (person.getLogin() == null || person.getLogin().isBlank()) {
            throw new IllegalArgumentException("Login must not be empty");
        }
        if (person.getPassword() == null || person.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password must not be empty");
        }
        if (personRepository.findByLogin(person.getLogin()).isPresent()) {
            throw new LoginAlreadyExistsException(person.getLogin());
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public void update(int id, Person person) {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        if (person.getLogin() == null || person.getLogin().isBlank()) {
            throw new IllegalArgumentException("Login must not be empty");
        }
        if (person.getPassword() == null || person.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password must not be empty");
        }
        personRepository.findByLogin(person.getLogin())
                .filter(p -> !p.getId().equals(id))
                .ifPresent(p -> {
                    throw new LoginAlreadyExistsException(person.getLogin());
                });
        person.setId(id);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    public void patch(int id, PersonPatchDto personPatchDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        if (personPatchDto.getPassword() == null || personPatchDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password must not be empty");
        }
        person.setPassword(passwordEncoder.encode(personPatchDto.getPassword()));
        personRepository.save(person);
    }

    public void delete(int id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        personRepository.delete(person);
    }
}
