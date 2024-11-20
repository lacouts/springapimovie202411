package org.example.movieapi.dto.tu;

import org.example.movieapi.dto.MovieDtoCreate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDtoCreateTest {

    @Test
    void testBuilder_default(){
        MovieDtoCreate movie = MovieDtoCreate.builder().build();
        assertAll(
                () -> assertNull(movie.getTitle(), "title"),
                () -> assertEquals(0, movie.getYear(), "year"),
                () -> assertNull(movie.getDuration(), "duration"),
                () -> assertNull(movie.getSynopsis(), "synopsis"),
                () -> assertNull(movie.getColor(), "color")
        );
    }

}