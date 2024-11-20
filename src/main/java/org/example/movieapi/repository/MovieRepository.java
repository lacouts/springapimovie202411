package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// implicitly: @Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @EntityGraph("Movie.directorAndActors")
    @Override
    Optional<Movie> findById(Integer integer);
}
