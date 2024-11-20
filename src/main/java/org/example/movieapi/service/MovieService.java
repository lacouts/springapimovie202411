package org.example.movieapi.service;

import org.example.movieapi.dto.MovieDtoCreate;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieDtoSimple add(MovieDtoCreate movieDto);
    Optional<MovieDtoDetail> getById(int id);
    List<MovieDtoSimple> getAll();
    MovieDtoSimple update(MovieDtoSimple movieDto);
    Optional<MovieDtoDetail> setDirector(int movieId, int directorId);
    Optional<MovieDtoDetail> setActors(int movieId, List<Integer> actorIds);
}
