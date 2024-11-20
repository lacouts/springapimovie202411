package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
public class MovieDtoSimple extends MovieDtoCreate {
    private int id;
}
