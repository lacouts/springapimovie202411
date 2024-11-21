package org.example.movieapi.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.entity.Movie_;
import org.example.movieapi.entity.Person_;
import org.example.movieapi.repository.MovieRepositoryExtension;

import java.util.List;

public class MovieRepositoryExtensionImpl implements MovieRepositoryExtension {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movie> findByDirectorNameCB(String directorName) {
        var cb = entityManager.getCriteriaBuilder();
        var q = cb.createQuery(Movie.class);
        var rootMovie = q.from(Movie.class);
        rootMovie.fetch(Movie_.director, JoinType.INNER);
        //var director = rootMovie.join(Movie_.director);
        q.select(rootMovie)
                .where(cb.equal(
                        cb.lower(rootMovie.get(Movie_.director).get(Person_.name)),
                        // director.get(Person_.name)), // director join
                        directorName.toLowerCase()));
        return entityManager.createQuery(q)
                .getResultList();
    }
}
