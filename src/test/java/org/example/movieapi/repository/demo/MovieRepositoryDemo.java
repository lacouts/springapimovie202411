package org.example.movieapi.repository.demo;

import jakarta.persistence.EntityManager;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.entity.Person;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Autowired
    PersonRepository personRepository;

    @Autowired
    EntityManager entityManager; // cache Hibernate, full API
    // TestEntityManager entityManager; // view on cache Hibernate with a partial API

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

        // add director
        var director = new Person("Denis Villeneuve", LocalDate.of(1967, 10, 3));
        personRepository.saveAndFlush(director); // SQL: insert

        movie.setDirector(director);
        movieRepository.flush(); // SQL: update
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

    @Order(3)
    @Test
    void demoAddActors(){
        entityManager.clear(); // empty Hibernate Cache
        var optMovie = movieRepository.findById(1); // SQL: select ... from movie where id = ?
        assertTrue(optMovie.isPresent());
        var movie = optMovie.get();

        // add cast in db
        var persons = List.of(
                new Person("Zendaya"),
                new Person("Timothée Chalamet")
        );
        personRepository.saveAll(persons); // SQL: 2x INSERT into person ...

        movie.getActors()   // SQL: select  from movie join play  => fetch actors: empty list
                .addAll(persons);  // SQL: 2x insert into play
        movieRepository.flush();
    }

    @Order(4)
    @Test
    void demoAddActorsAgain(){
        entityManager.clear(); // empty Hibernate Cache
        var optMovie = movieRepository.findById(1);
        // SQL: select ... from movie join person  where id = ?
        //      Fetch = eager on director (Many to One default setting)
        assertTrue(optMovie.isPresent());
        var movie = optMovie.get();

        // add cast in db
        var persons = List.of(
                new Person("Rebecca Ferguson"),
                new Person("Javier Bardem")
        );
        personRepository.saveAll(persons); // SQL: 2x INSERT into person ...

        movie.getActors()
                // SQL: select  from movie join play  => fetch actors: 2 actors
                //      Fetch: lazy (default setting Many to Many)
                .addAll(persons);  // SQL: 2x insert into play
        movieRepository.flush();
    }

}