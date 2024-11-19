package org.example.movieapi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA
@Entity
// lombok
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    //@Column(name = "release_year")
    private int year;
}
