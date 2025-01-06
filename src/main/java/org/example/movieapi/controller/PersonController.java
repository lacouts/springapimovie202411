package org.example.movieapi.controller;

import org.example.movieapi.dto.PersonDtoCreate;
import org.example.movieapi.dto.PersonDtoSimple;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @PostMapping
    public PersonDtoSimple add(@RequestBody @Valid PersonDtoCreate person){
        return null;
    }

}
