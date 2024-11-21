package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // method with Spring JPA vocabulary (lookup): generate SQL automatically
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Movie> findByYear(int year);

    List<Movie> findByActorsNameContainsIgnoreCase(String partialActorName);

    List<Movie> findByDurationNotNullAndYearBetweenAndTitleContainingIgnoreCase(
            int yearBefore,
            int yearAfter,
            String partialTitle
    );

    // Query in JPQL
    // params: ?1, ?2 or :name :year with/without @Param
    // params with like: ... like %?1 or ... like %:name
    @Query("select m from Movie m join fetch m.director d where d.name = :name")
    List<Movie> findByDirector(String name);

    @Query("select m from Movie m join fetch m.director d where lower(d.name) like %:partialLowerName%")
    List<Movie> findByDirectorPartial(String partialLowerName);

    @Query("select m from Movie m join fetch m.director d where lower(d.name) like %:partialLowerName%")
    List<Movie> findByDirectorPartial(String partialLowerName, Sort sort);

    @Query("select m from Movie m join fetch m.director d where lower(d.name) like %:partialLowerName%")
    List<Movie> findByDirectorPartial(String partialLowerName, Pageable pageable);

    // NB: use named query defined in entity or orm.xml
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.named-queries
    List<Movie> findByTitleYearDuration(String title, int firstYear, int lastYear, int minDuration, int maxDuration);

}
