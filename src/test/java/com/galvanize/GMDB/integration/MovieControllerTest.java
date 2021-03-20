package com.galvanize.GMDB.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.GMDB.Pojo.Movie;
import com.galvanize.GMDB.Service.MovieService;
import com.galvanize.GMDB.request.MovieRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        MovieService.movieList = new ArrayList<>();
    }

    @Test
    public void getMovieWhenNoneExistTest() throws Exception {
        RequestBuilder go = get("/movies");
        mockMvc.perform(go)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andDo(print());
    }

    @Test
    public void postMovieWhenReleasedTest() throws Exception {
        MovieRequest mr = new MovieRequest("Aliens");
        RequestBuilder post = post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mr));

        mockMvc.perform(post)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("message").value("Movie has been created."))
                .andDo(print());

        RequestBuilder get = get("/movies");
        mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Aliens"))
                .andDo(print());
    }

    @Test
    public void getMovieWhenAMovieExistTest() throws Exception {
        Movie movie = new Movie();
        movie.setName("Avengers");
        MovieService.movieList.add(movie);
        RequestBuilder go = get("/movies");
        mockMvc.perform(go)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Avengers"))
                .andDo(print());
    }
}