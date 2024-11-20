package org.example.movieapi.config;

import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.PersonDtoSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        var modelMapper =  new ModelMapper();
        // here tune mapping
        // Example: MovieEntity.title => MovieSimple.original_title
        // https://modelmapper.org/user-manual/
////        modelMapper.typeMap(Movie.class, MovieDtoDetail.class)
////                .addMapping(Movie::getActors, (dest, actors) -> dest.setActors(
////                        actors.stream()
////                                .map(actorEntity -> modelMapper.map(actorEntity, PersonDtoSimple.class))
////                                .toSet()
////                ));
        return modelMapper;
    }
}
