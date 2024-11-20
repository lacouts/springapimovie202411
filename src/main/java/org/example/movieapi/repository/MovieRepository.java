package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// implicitly: @Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    // NB: result type for entity Movie can be: Movie, Optional<Movie>, List<Movie>, Set<Movie>, Stream<Movie>

    @EntityGraph("Movie.directorAndActors")
    @Override
    Optional<Movie> findById(Integer integer);

    // method with Spring JPA vocabulary: generate SQL automatically
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Movie> findByYear(int year);

    List<Movie> findByActorsNameContainsIgnoreCase(String partialActorName);

    List<Movie> findByDurationNotNullAndYearBetweenAndTitleContainingIgnoreCase(
            int yearBefore,
            int yearAfter,
            String partialTitle
    );

    // Query in JPQL
    @Query("select m from Movie m join fetch m.director d where d.name = :name")
    List<Movie> findByDirector(String name);

    @Query("select m from Movie m join fetch m.director d where lower(d.name) like %?1")
    List<Movie> findByDirectorPartial(String partialLowerName);
}
