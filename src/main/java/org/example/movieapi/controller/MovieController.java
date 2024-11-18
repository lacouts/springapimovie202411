package org.example.movieapi.controller;

import org.example.movieapi.dto.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @GetMapping
    public Movie randomMovie(){
        // var movie = new Movie();
        // movie.setTitle("Dune");
        // return movie;
        return new Movie("Dune: Part Two", 2024);
    }

    //public String hello(){
    //    return "Hello, I need coffee !";
    //}
}
