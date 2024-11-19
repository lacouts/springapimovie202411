package org.example.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MovieDto {
    private Integer id;
    private String title;
    private int year;
}
