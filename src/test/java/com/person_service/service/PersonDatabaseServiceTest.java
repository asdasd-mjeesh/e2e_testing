package com.person_service.service;

import com.person_service.IntegrationTestBase;
import com.person_service.entity.Person;
import com.person_service.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonDatabaseServiceTest extends IntegrationTestBase {
    private static final Long TEST_ID = 1L;
    private Person testPerson;

    @MockBean
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @BeforeEach
    void setUp() {
        testPerson = Person.builder()
                .id(TEST_ID)
                .firstName("test_name")
                .lastName("test_surname")
                .birthDate(LocalDate.of(2002, 12, 25))
                .build();
    }

    @Test
    void create() {
        when(personRepository.save(testPerson)).thenReturn(testPerson);
        Person creationResult = personService.create(testPerson);

        verify(personRepository, times(1)).save(testPerson);
        assertEquals(creationResult, testPerson);
    }

    @Test
    void getById() {
        when(personRepository.findById(TEST_ID)).thenReturn(Optional.of(testPerson));
        Optional<Person> personFromService = personService.getById(TEST_ID);

        verify(personRepository, times(1)).findById(TEST_ID);
        assertEquals(personFromService, Optional.of(testPerson));
    }
}
