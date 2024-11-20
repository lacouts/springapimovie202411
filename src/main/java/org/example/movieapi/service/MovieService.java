package org.example.movieapi.service;

import org.example.movieapi.dto.MovieDto;

public interface MovieService {
    MovieDto add(MovieDto movieDto);
    MovieDto getById(int id);
}
