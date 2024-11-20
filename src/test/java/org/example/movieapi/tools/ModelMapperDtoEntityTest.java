package org.example.movieapi.tools;

import org.example.movieapi.dto.MovieDtoSimple;
import org.example.movieapi.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

public class ModelMapperDtoEntityTest {

    // TODO: get model mapper from config class
    static ModelMapper modelMapper = new ModelMapper();

    @Test
    void mapMovieEntityToMovieDtoSimple(){
        var movieEntity = Movie.builder()
                .id(123)
                .title("Dune: Part II")
                .year(2024)
                // other fields
                .build();
        var movieDto = modelMapper.map(movieEntity, MovieDtoSimple.class);
        assertAll(
                () -> assertEquals(123, movieDto.getId(), "id"),
                () -> assertEquals("Dune: Part II", movieDto.getTitle(), "title"),
                () -> assertEquals(2024, movieDto.getYear(), "year")
        );

    }
}
