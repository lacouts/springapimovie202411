package org.example.movieapi.controller;

import jakarta.validation.Valid;
import org.example.movieapi.dto.MovieDtoCreate;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;
import org.example.movieapi.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    // NB: can be generated with lombok @Slf4j
    Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    /**
     * route: /api/movie
     * get all movies (pagination, default filter)
     * @return movies
     */
    @GetMapping
    public List<MovieDtoSimple> allMovies(){
        return movieService.getAll();
    }

    /**
     * route: /api/movie/{id}
     * get a movie with its id
     * @return the movie with this id
     */
    @GetMapping("/{id}")
    public MovieDtoDetail movieById(@PathVariable("id") int id){
        return movieService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        MessageFormat.format("Movie not found with id <{0}>", id)
                ));
    }

    // Method with wrapper around response: ResponseEntity
    // - body
    // - headers, http status
    @GetMapping("/{id}/alt")
    public ResponseEntity<?> movieByIdWrapper(@PathVariable("id") int id){
        var optMovie = movieService.getById(id);
        if (optMovie.isPresent()) {
            return ResponseEntity.ok(optMovie.get()); // returns ResponseEntity<Movie>
        } else {
            return ResponseEntity.notFound().build(); // return ResponseEntity<?>
        }
    }

    @GetMapping("/search")
    public List<MovieDtoCreate> search(
            @RequestParam(name = "y", required = false) Integer year,
            @RequestParam(name = "t", required = false) String titlePart
    ){
//        if (Objects.nonNull(year) && Objects.isNull(titlePart)){
//            return movieRepository.findAll(Example.of(
//                    MovieDto.builder().year(year).build()
//            ));
//        } else {
//            throw new NotImplementedException("Search not implemented with this criteria");
//        }
        return null;
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
    public MovieDtoSimple addMovie(@Valid @RequestBody MovieDtoCreate movie){
        var movieResponse =  movieService.add(movie);
        log.info("Movie added: {}", movieResponse);
        return movieResponse;
    }

}
