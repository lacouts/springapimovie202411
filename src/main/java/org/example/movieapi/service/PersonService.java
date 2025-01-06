package org.example.movieapi.service;

import java.util.List;
import java.util.Optional;

import org.example.movieapi.dto.PersonDtoCreate;
import org.example.movieapi.dto.PersonDtoSimple;


public interface PersonService {
    Optional<PersonDtoSimple> getById(int id);
    List<PersonDtoSimple> getAll();
    PersonDtoSimple add(PersonDtoCreate person);
}
