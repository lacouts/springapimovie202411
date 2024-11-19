package org.example.movieapi.controller;

import org.apache.commons.lang3.NotImplementedException;
import org.example.movieapi.dto.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * route: /api/movie
     * get all movies (pagination, default filter)
     * @return movies
     */
    @GetMapping
    public List<Movie> allMovies(){
//        return List.of(
//            new Movie("Dune I", 2019),
//            new Movie("Dune II", 2024)
//        );
        return movieRepository.findAll();
    }

    /**
     * route: /api/movie/{id}
     * get a movie with its id
     * @return the movie with this id
     */
    @GetMapping("/{id}")
    public Movie movieById(@PathVariable("id") int id){
        // return new Movie("Ocean", 2024);
        return movieRepository.findById(id)
                .orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
        // NB: exception ErrorResponseException mapped with HTTP Status 404
    }

    // Method with wrapper around response: ResponseEntity
    // - body
    // - headers, http status
    @GetMapping("/{id}/alt")
    public ResponseEntity<?> movieByIdWrapper(@PathVariable("id") int id){
        var optMovie = movieRepository.findById(id);
        if (optMovie.isPresent()) {
            return ResponseEntity.ok(optMovie.get()); // returns ResponseEntity<Movie>
        } else {
            return ResponseEntity.notFound().build(); // return ResponseEntity<?>
        }
    }

    @GetMapping("/search")
    public List<Movie> search(
            @RequestParam(name = "y", required = false) Integer year,
            @RequestParam(name = "t", required = false) String titlePart
    ){
        if (Objects.nonNull(year) && Objects.isNull(titlePart)){
            return movieRepository.findAll(Example.of(
                    Movie.builder().year(year).build()
            ));
        } else {
            throw new NotImplementedException("Search not implemented with this criteria");
        }
    }


    /**
     * route: /api/movie
     * add a new Movie
     * @param movie movie to add
     * @return movie added
     */
    @PostMapping(
            consumes = {
                    MimeTypeUtils.APPLICATION_JSON_VALUE, // default
                    MimeTypeUtils.APPLICATION_XML_VALUE, // need XML converter + @XmlRootElement
            },
            produces = {
                    MimeTypeUtils.APPLICATION_JSON_VALUE, // default
                    MimeTypeUtils.APPLICATION_XML_VALUE,
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Movie addMovie(@RequestBody Movie movie){
        return movieRepository.saveAndFlush(movie);
    }

}
