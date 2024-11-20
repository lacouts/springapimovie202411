package org.example.movieapi.dto;

import lombok.*;
import org.example.movieapi.enums.MovieColor;

// lombok
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MovieDtoCreate {
    private String title;
    private int year;
    private Integer duration;
    private String synopsis;
    private MovieColor color;
}
