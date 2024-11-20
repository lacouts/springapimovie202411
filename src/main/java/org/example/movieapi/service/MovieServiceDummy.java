package org.example.movieapi.service;

import org.example.movieapi.dto.MovieDtoCreate;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("dummy")
@Service
public class MovieServiceDummy implements MovieService{

    @Override
    public MovieDtoSimple add(MovieDtoCreate movieDto) {
        return null;
    }

    @Override
    public Optional<MovieDtoDetail> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<MovieDtoSimple> getAll() {
        return List.of();
    }

    @Override
    public MovieDtoSimple update(MovieDtoSimple movieDto) {
        return null;
    }

    @Override
    public Optional<MovieDtoDetail> setDirector(int movieId, int directorId) {
        return Optional.empty();
    }

    @Override
    public Optional<MovieDtoDetail> setActors(int movieId, List<Integer> actorIds) {
        return Optional.empty();
    }
}
