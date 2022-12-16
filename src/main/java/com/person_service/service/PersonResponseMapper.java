package com.person_service.service;

import com.person_service.dto.PersonResponse;
import com.person_service.entity.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonResponseMapper implements Mapper<PersonResponse, Person> {

    @Override
    public PersonResponse map(Person from) {
        return PersonResponse.builder()
                .id(from.getId())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .age(Period.between(from.getBirthDate(), LocalDate.now()).getYears())
                .build();
    }

    @Override
    public List<PersonResponse> map(List<Person> from) {
        return from.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
