package org.example.movieapi.controller.tu;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

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

        mockMvc.perform(
                // build HTTP GET request
                MockMvcRequestBuilders.get(URL_BASE + "/" + id)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

}