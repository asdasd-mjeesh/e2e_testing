package com.person_service.service;

import com.person_service.entity.Person;

import java.util.Optional;

public interface PersonService {
    Person create(Person person);
    Optional<Person> getById(Long id);
}
