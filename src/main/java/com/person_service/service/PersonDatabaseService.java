package com.person_service.service;

import com.person_service.entity.Person;
import com.person_service.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDatabaseService implements PersonService {
    private final PersonRepository personRepository;

    public PersonDatabaseService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }
}
