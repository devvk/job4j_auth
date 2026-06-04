package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Person create(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public boolean update(int id, Person person) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson == null) {
            return false;
        }

        if (person.getPassword() != null && !person.getPassword().isBlank()) {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
        } else {
            person.setPassword(existingPerson.getPassword());
        }

        person.setId(id);
        personRepository.save(person);
        return true;
    }

    public boolean delete(int id) {
        if (!personRepository.existsById(id)) {
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }
}
