package com.person_service.config;

import com.person_service.entity.Person;
import com.person_service.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class EnvironmentInitConfig {

    @Bean
    CommandLineRunner putEmployeesToTheDatabase(PersonRepository personRepository) {
        if (personRepository.count() > 0) {
            return null;
        }
        return args -> {
            personRepository.save(Person.builder()
                    .firstName("John")
                    .lastName("Tarasov")
                            .birthDate(LocalDate.of(2001, 12, 4))
                    .build());
            personRepository.save(Person.builder()
                    .firstName("Oleksii")
                    .lastName("Borsykov")
                    .birthDate(LocalDate.of(1337, 5, 30))
                    .build());
            personRepository.save(Person.builder()
                    .firstName("Oleh")
                    .lastName("Johnson")
                    .birthDate(LocalDate.of(2005, 5, 9))
                    .build());
            personRepository.save(Person.builder()
                    .firstName("Mykola")
                    .lastName("Piterson")
                    .birthDate(LocalDate.of(1992, 9, 11))
                    .build());
            personRepository.save(Person.builder()
                    .firstName("Mickle")
                    .lastName("Ivanov")
                    .birthDate(LocalDate.of(1982, 7, 6))
                    .build());
        };
    }
}
