package com.galvanize.GMDB.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.GMDB.Entity.Movie;
import com.galvanize.GMDB.repository.MovieRepository;
import com.galvanize.GMDB.request.RatingRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    public void init() {
        movieRepository.deleteAll();
    }

    @Test
    public void submitRatingToMovieTest() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Avengers");
        movie.setDirector("Joss");
        movie.setActors("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth");
        movie.setDescription("Avengeeeeeeee");
        movie.setReleasedYear("2012");
        movieRepository.save(movie);

        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setMovieTitle("Avengers");
        ratingRequest.setStars(4);

        RequestBuilder rq = post("/rating")
                .content(objectMapper.writeValueAsString(ratingRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq).andExpect(status().isCreated())
        .andExpect(jsonPath("message").value("A rating has been added"));
    }
}
