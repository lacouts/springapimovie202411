package org.example.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class MovieDtoDetail extends MovieDtoSimple{
    private PersonDtoSimple director;
    private Set<PersonDtoSimple> actors;
}
