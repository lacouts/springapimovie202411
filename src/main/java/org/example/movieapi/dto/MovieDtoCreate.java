package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.movieapi.enums.MovieColor;

// lombok
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(exclude = {"synopsis", "color"})
public class MovieDtoCreate {
    private String title;
    private int year;
    private Integer duration;
    private String synopsis;
    private MovieColor color;
}
