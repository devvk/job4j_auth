package ru.job4j.auth.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import ru.job4j.auth.dto.PersonPatchDto;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
@Validated
public class PersonController {

    private final PersonService personService;

    @PostMapping("/sign-up")
    public ResponseEntity<Person> signUp(@Valid @RequestBody Person person) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.create(person));
    }

    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable @Positive(message = "Id must be positive") int id) {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable @Positive(message = "Id must be positive") int id,
                                       @Valid @RequestBody Person person) {
        personService.update(id, person);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable @Positive(message = "Id must be positive") int id,
                                      @Valid @RequestBody PersonPatchDto dto) {
        personService.patch(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = "Id must be positive") int id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
