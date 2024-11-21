package org.example.movieapi.service;

import org.example.movieapi.dto.MovieDtoCreate;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieDtoSimple add(MovieDtoCreate movieDto);

    /**
     *
     * @param id
     * @return Optional empty if not found
     */
    Optional<MovieDtoDetail> getById(int id);
    List<MovieDtoSimple> getAll();

    /**
     *
     * @param movieDto
     * @return Optional empty if not found
     */
    Optional<MovieDtoSimple> update(MovieDtoSimple movieDto);

    /**
     *
     * @param movieId
     * @param directorId
     * @return Optional empty if either movie or director is not found
     */
    Optional<MovieDtoDetail> setDirector(int movieId, int directorId);

    /**
     *
     * @param movieId
     * @param actorIds
     * @return Optional empty if either movie or any actor is not found
     */
    Optional<MovieDtoDetail> setActors(int movieId, List<Integer> actorIds);

    /**
     *
     * @param id
     * @return false if not found
     */
    boolean delete(int id);
}
