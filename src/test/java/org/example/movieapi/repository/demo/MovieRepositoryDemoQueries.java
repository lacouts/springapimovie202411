package org.example.movieapi.repository.demo;

import jakarta.persistence.EntityManager;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Stream;

@DataJpaTest
@ActiveProfiles("demoquery")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryDemoQueries {

    @Autowired
    MovieRepository movieRepository; // Spring repository over Hibernate

    @Autowired
    EntityManager entityManager; // Direct access to Hibernate

    @Test
    void demoFindAll(){
        var movies = movieRepository.findAll();
        movies.stream()
                .limit(10)
                .forEach(System.out::println);
    }

    // findById : cf MovieRepositoryDemoCrud
    // findAll with Example : cf MovieRepositoryDemoCrud

    //
    static Stream<Sort> demoFindAllSortSource() {
        return Stream.of(
                Sort.by("year"),
                Sort.by("year", "title"),
                Sort.by(Sort.Order.by("year").reverse(), Sort.Order.by("title"))
        );
    }

    @ParameterizedTest
    @MethodSource("demoFindAllSortSource")
    void demoFindAllSort(Sort sort){
        var movies = movieRepository.findAll(sort); // SQL: ... order by
        movies.stream()
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    void demoFindByYear(){
        int year = 1984;
        var movies = movieRepository.findByYear(year);
        movies.stream()
                .limit(10)
                .forEach(System.out::println);

    }

    @Test
    void demofindByActorsNameContainsIgnoreCase(){
        var movies = movieRepository.findByActorsNameContainsIgnoreCase("eastwood");
        movies.stream()
                .limit(10)
                .forEach(movie -> {
                    System.out.println(movie);
                    movie.getActors().stream()
                            .filter(actor -> actor.getName().toLowerCase().contains("eastwood"))
                            .forEach(actor -> System.out.println("\t- " + actor.getName()));
                });
    }

    // movie with duration not null, year in [1980-1989], title containing '...' (CI)
    @Test
    void demoFindByDurationNotNullAndYearBetweenAndTitleContainingIgnoreCase() {
        String titlePart = "conan";
        int year1 = 1980;
        int year2 = 1989;
        var movies = movieRepository.findByDurationNotNullAndYearBetweenAndTitleContainingIgnoreCase(year1, year2, titlePart);
        movies.stream()
                .limit(10)
                .forEach(movie -> System.out.println(MessageFormat.format(
                        "{0} with duration {1}", movie, movie.getDuration())));
    }

    @Test
    void demoFindByDirector(){
        var movies = movieRepository.findByDirector("Clint Eastwood");
        movies.stream()
                .limit(10)
                .forEach(movie -> System.out.println(MessageFormat.format(
                        "{0} with director {1}", movie, movie.getDirector().getName())));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "clint",
            "eastwood",
            "clint eastwood"
    })
    void demoFindByDirectorPartial(String partialName){
        var movies = movieRepository.findByDirectorPartial(partialName);
        movies.stream()
                .limit(10)
                .forEach(movie -> System.out.println(MessageFormat.format(
                        "{0} with director {1}", movie, movie.getDirector().getName())));
    }

    @Test
    void demoFindByTitleYearDuration() {
        String title = "star";
        int year1 = 1980;
        int year2 = 1989;
        int duration1 = 120;
        int duration2 = Integer.MAX_VALUE;
        var movies = movieRepository.findByTitleYearDuration(title, year1, year2, duration1, duration2);
        movies.forEach(movie -> System.out.println(MessageFormat.format(
                        "{0} with duration {1}", movie, movie.getDuration())));
    }
}
