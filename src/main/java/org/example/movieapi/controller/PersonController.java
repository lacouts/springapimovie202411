package org.example.movieapi.controller;

import org.example.movieapi.dto.PersonDtoCreate;
import org.example.movieapi.dto.PersonDtoSimple;
import org.example.movieapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PersonDtoSimple add(@RequestBody PersonDtoCreate person){
        return personService.add(person);
    }

}
