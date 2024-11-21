package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;

import java.util.List;

public interface MovieRepositoryExtension {
    List<Movie> findByDirectorNameCB(String directorName);
}
