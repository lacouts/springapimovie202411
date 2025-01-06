package org.example.movieapi.controller.tu;

import org.example.movieapi.controller.PersonController;
import org.example.movieapi.controller.tu.assertions.Matchers;
import org.example.movieapi.controller.tu.fixture.JsonProviders;
import org.example.movieapi.dto.MovieDtoSimple;
import org.example.movieapi.dto.PersonDtoSimple;
import org.example.movieapi.service.MovieService;
import org.example.movieapi.service.PersonService;
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

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    final static String URL_BASE = "/api/person";

    @Autowired
    MockMvc mockMvc; // Mock http client to test api controller

    @MockBean
    PersonService personService;

     void testAdd_valid() throws Exception {
        // given
        // JSON person to add
        var name = "Daniel Craig";
        var personJSON = "{\"name\": \"Daniel Craig\"}";
        // DTO person returned by mock service
        int id = 321;
        var personDto = PersonDtoSimple.builder()
                .id(id)
                .name(name)
                .build();
        BDDMockito.given(personService.add(ArgumentMatchers.any()))
                .willReturn(personDto);

        // 2 - when
        mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJSON))
                .andDo(MockMvcResultHandlers.print())
                // 3a - then/verify
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name));
            
        // 3b - verify: mock service has been called
        BDDMockito.then(personService)
                .should()
                .add(ArgumentMatchers.any());
    }
}
