package org.example.movieapi.repository.demo;

import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
// start hibernate
// by default use a different H2 database from application

@ActiveProfiles("demo")
// search application-demo.properties|application-demo.yml
// in directories test\resources, then main\resources, ...
// int this profile configure a specific test database

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// keep database configuration (here: base demo) and do not replace with a new H2 db

@Rollback(value = false) // default = true, very useful for unit testing
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieRepositoryDemo {

    @Autowired
    MovieRepository movieRepository;

    @Order(1)
    @Test
    void demoSave(){
        // new entity in memory
        var movie = Movie.builder()
                .title("Dune: Part II")
                .year(2024)
                .build();
        movieRepository.save(movie); // SQL: next value from sequence (with strategy sequence)
        assertNotNull(movie.getId());
        System.out.println(movie);
        movieRepository.flush(); // synchro cache Hibernate <-> database => SQL: INSERT
        System.out.println(movie);
    }

    @Order(2)
    @Test
    void demoFindWithExample(){
        // https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html
        var movieExample = Movie.builder()
                .title("dune")
                .build();
        var exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("year")
                .withMatcher("title", match -> match.contains().ignoreCase());
        var movies = movieRepository.findAll(Example.of(movieExample, exampleMatcher));
        System.out.println(movies);
    }

}