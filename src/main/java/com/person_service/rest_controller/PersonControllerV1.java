package com.person_service.rest_controller;

import com.person_service.dto.PersonResponse;
import com.person_service.entity.Person;
import com.person_service.service.PersonResponseMapper;
import com.person_service.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonControllerV1 {
    private final PersonService personService;
    private final PersonResponseMapper personResponseMapper;

    public PersonControllerV1(PersonService personService, PersonResponseMapper personResponseMapper) {
        this.personService = personService;
        this.personResponseMapper = personResponseMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        var person = personService.getById(id);
        if (person.isPresent()) {
            var personResponse = personResponseMapper.map(person.get());
            return ResponseEntity.ok(personResponse);
        }
        return new ResponseEntity<>(String.format("Person with id=%s not found", id), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
        var createdPerson =  personService.create(person);
        var createdPersonResponse = personResponseMapper.map(createdPerson);
        return new ResponseEntity<>(createdPersonResponse, HttpStatus.CREATED);
    }
}
