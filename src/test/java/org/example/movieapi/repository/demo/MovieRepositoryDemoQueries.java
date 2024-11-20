package org.example.movieapi.repository.demo;

import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("demoquery")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryDemoQueries {

    @Autowired
    MovieRepository movieRepository;

    @Test
    void demoFindAll(){
        var movies = movieRepository.findAll();
        movies.stream()
                .limit(10)
                .forEach(System.out::println);
    }
}
