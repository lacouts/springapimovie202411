package org.example.movieapi.repository;

import org.example.movieapi.dto.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

// implicitly: @Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {


}
