package com.person_service.rest_controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.person_service.IntegrationTestBase;
import com.person_service.dto.PersonResponse;
import com.person_service.entity.Person;
import com.person_service.service.PersonResponseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import com.person_service.repository.PersonRepository;

@AutoConfigureMockMvc
class PersonControllerV1Test extends IntegrationTestBase {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonResponseMapper personResponseMapper;

    @Test
    void getById() throws Exception {
        Person person = Person.builder()
                .firstName("TestEmployee name")
                .lastName("TestEmployee surname")
                .birthDate(LocalDate.of(2003, 10, 11))
                .build();
        person = personRepository.save(person);
        PersonResponse personResponse = personResponseMapper.map(person);

        mvc.perform(get("/api/v1/persons/" + person.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", is(personResponse.getFirstName())))
                .andExpect(jsonPath("lastName", is(personResponse.getLastName())))
                .andExpect(jsonPath("age", is(personResponse.getAge())));
    }
}
