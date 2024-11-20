package org.example.movieapi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.movieapi.dto.MovieDtoCreate;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j // create logger for this class: attribute log
@Profile("jpa")
@Service
public class MovieServiceJpa implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MovieDtoSimple add(MovieDtoCreate movieDto) {
        Movie movieEntity = modelMapper.map(movieDto, Movie.class);
        movieRepository.saveAndFlush(movieEntity);
        log.debug("Movie added in database: {}", movieEntity);
        return modelMapper.map(movieEntity, MovieDtoSimple.class);
    }

    @Override
    public Optional<MovieDtoDetail> getById(int id) {
        return movieRepository.findById(id)
                .map(movieEntity -> modelMapper.map(movieEntity, MovieDtoDetail.class));
    }

    @Override
    public List<MovieDtoSimple> getAll() {
        return movieRepository.findAll(Sort.by("year", "title"))
                .stream()
                .map(movieEntity -> modelMapper.map(movieEntity, MovieDtoSimple.class))
                .toList();
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
