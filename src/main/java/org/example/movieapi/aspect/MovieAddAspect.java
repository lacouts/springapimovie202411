package org.example.movieapi.aspect;

import org.aspectj.lang.annotation.*;
import org.example.movieapi.dto.MovieDtoCreate;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MovieAddAspect {

    private static final Logger log = LoggerFactory.getLogger(MovieAddAspect.class);

    @Before(
            value = "execution(* org.example.movieapi.service.MovieServiceJpa.add(org.example.movieapi.dto.MovieDtoCreate))" +
                    " && args(movie)"
    )
    public void beforeMovieAdded(MovieDtoCreate movie) {
        log.debug("Movie about to be added: {}", movie);
    }

    @After("execution(* org.example.movieapi.service.MovieServiceJpa.add(..))")
    public void afterMovieAdded(){
        log.debug("Movie added has been called");
    }

    @AfterReturning(
            pointcut = "execution(* org.example.movieapi.service.MovieServiceJpa.add(..))",
            returning = "movieAdded"
    )
    public void afterReturningMovieAdded(MovieDtoSimple movieAdded){
        log.info("Movie added: {}", movieAdded);
    }

    @AfterThrowing(
            pointcut = "execution(* org.example.movieapi.service.MovieServiceJpa.add(..))",
            throwing = "error"
    )
    public void afterReturningMovieAdded(Exception error){
        log.error("Movie add failed: {}", error.getMessage());
    }
}