package org.example.movieapi.controller.tu;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.controller.tu.assertions.Matchers;
import org.example.movieapi.controller.tu.fixture.JsonProviders;
import org.example.movieapi.dto.MovieDtoDetail;
import org.example.movieapi.dto.MovieDtoSimple;
import org.example.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Spring test for api|mvc controller
@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    final static String URL_BASE = "/api/movie";

    @Autowired
    MockMvc mockMvc; // Mock http client to test api controller

    @MockBean
    MovieService movieService;

    @Test
    void testMovieById_notFound() throws Exception {
        int id = 0;

        // prepare mock
        Optional<MovieDtoDetail> optMovieDtoDetailMocked = Optional.empty();
        BDDMockito.given(movieService.getById(id))
                        .willReturn(optMovieDtoDetailMocked);

        // call controller
        mockMvc.perform(
                // build HTTP GET request
                MockMvcRequestBuilders.get(URL_BASE + "/" + id)
                        .accept(MediaType.APPLICATION_JSON)
        )
                // debug: print request and response
                .andDo(MockMvcResultHandlers.print())
                // verify response
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;

        // verify mock service has been called
        BDDMockito.then(movieService)
                .should()
                .getById(id);
    }

    @Test
    void testMovieById_found() throws Exception {
        int id = 123;

        // prepare mock
        String title = "Dune: Part II";
        int year = 2024; // + other properties ...
        Optional<MovieDtoDetail> optMovieDtoDetailMocked = Optional.of(
                MovieDtoDetail.builder()
                        .id(id)
                        .title(title)
                        .year(year)
                        // + other properties: director, actors
                        .build()
        );
        BDDMockito.given(movieService.getById(id))
                .willReturn(optMovieDtoDetailMocked);

        // call controller
        mockMvc.perform(
                        // build HTTP GET request
                        MockMvcRequestBuilders.get(URL_BASE + "/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                )
                // debug: print request and response
                .andDo(MockMvcResultHandlers.print())
                // verify response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(year))
                    // very other properties
        ;

        // verify mock service has been called
        BDDMockito.then(movieService)
                .should()
                .getById(id);
    }

    @ParameterizedTest
    @CsvSource(quoteCharacter = '|', value = {
            "The Batman,2022,,",
            "Z,1969,,",
            "|Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil Mutant Hellbound Flesh Eating Crawling Alien Zombified Subhumanoid Living Dead, Part 5|,2011,,",
            "The Batman,2022,176,",
            "The Batman,2022,,|When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.|",
            "The Batman,2022,176,|When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.|",
    })
    void testAdd_valid(String title, int year, Integer duration, String synopsis) throws Exception {
        // given
        // JSON movie to add
        var movieJSON = JsonProviders.movieJSON(title, year, duration, synopsis);
        // DTO movie returned by mock service
        int id = 321;
        var movieDto = MovieDtoSimple.builder()
                .id(id)
                .title(title)
                .year(year)
                .duration(duration)
                .synopsis(synopsis)
                .build();
        BDDMockito.given(movieService.add(ArgumentMatchers.any()))
                .willReturn(movieDto);

        // 2 - when
        mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJSON))
                .andDo(MockMvcResultHandlers.print())
                // 3a - then/verify
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value((int) year))
                .andExpect(Matchers.jsonPathIsNullOrEquals("$.duration", duration))
                .andExpect(Matchers.jsonPathIsNullOrEquals("$.synopsis", synopsis));

        // 3b - verify: mock service has been called
        BDDMockito.then(movieService)
                .should()
                .add(ArgumentMatchers.any());
    }
}