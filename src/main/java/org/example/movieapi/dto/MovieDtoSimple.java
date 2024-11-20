package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class MovieDtoSimple extends MovieDtoCreate {
    private int id;
}
