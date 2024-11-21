package org.example.movieapi.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDtoStatsByYear {
    private int year;
    private long movieCount;
    private Integer minDuration;
    private Integer maxDuration;
    private Double avgDuration;
}
