package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class PersonDtoCreate {
    @NotEmpty
    private String name;
    private LocalDate birthdate;
}
