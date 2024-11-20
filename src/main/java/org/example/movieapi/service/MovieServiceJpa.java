package org.example.movieapi.service;

import org.example.movieapi.dto.MovieDto;
import org.example.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceJpa implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDto add(MovieDto movieDto) {
        return null;
    }

    @Override
    public MovieDto getById(int id) {
        return null;
    }
}
