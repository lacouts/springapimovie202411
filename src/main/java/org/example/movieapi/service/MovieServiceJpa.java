package org.example.movieapi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.movieapi.dto.MovieDtoCreate;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.errors.NotFoundException;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.repository.PersonRepository;
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

    // @Autowired
    private MovieRepository movieRepository;

    // @Autowired
    private PersonRepository personRepository;

    // @Autowired
    private ModelMapper modelMapper;

    public MovieServiceJpa(MovieRepository movieRepository, PersonRepository personRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

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
    public Optional<MovieDtoSimple> update(MovieDtoSimple movie) {
        return  movieRepository.findById(movie.getId())
                .map(movieEntity -> {
                    modelMapper.map(movie, movieEntity);
                    movieRepository.saveAndFlush(movieEntity);
                    return modelMapper.map(movieEntity, MovieDtoSimple.class);
                });
    }

    @Override
    public Optional<MovieDtoDetail> setDirector(int idMovie, int idDirector) {
        return movieRepository.findById(idMovie)
                .flatMap(movieEntity -> personRepository.findById(idDirector)
                        .map(directorEntity -> {
                            movieEntity.setDirector(directorEntity);
                            movieRepository.saveAndFlush(movieEntity);
                            return modelMapper.map(movieEntity, MovieDtoDetail.class);
                        })
                );
    }

    @Override
    public Optional<MovieDtoDetail> setActors(int movieId, List<Integer> actorIds) {
        return movieRepository.findById(movieId)
                .flatMap(movieEntity -> {
                    var actorEntities = personRepository.findAllById(actorIds);
                    if (actorEntities.size() != actorIds.size()) return Optional.empty();
                    // update actors list
                    movieEntity.getActors().clear();
                    movieEntity.getActors()
                            .addAll(actorEntities);
                    movieRepository.flush();
                    return Optional.of(modelMapper.map(movieEntity, MovieDtoDetail.class));
                });
    }

    @Override
    public boolean delete(int id) {
        return movieRepository.findById(id)
                .map(movieEntity -> {
                    movieRepository.deleteById(id);
                    movieRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
