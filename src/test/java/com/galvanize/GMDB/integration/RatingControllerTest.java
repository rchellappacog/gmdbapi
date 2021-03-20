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


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        ratingRequest.setStars(5);

        RequestBuilder rq = post("/rating")
                .content(objectMapper.writeValueAsString(ratingRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq).andExpect(status().isCreated())
        .andExpect(jsonPath("message").value("A rating has been added"));

        RequestBuilder get = get("/movies/Avengers");
        mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Avengers"))
                .andExpect(jsonPath("director").value("Joss"))
                .andExpect(jsonPath("actors").value("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth"))
                .andExpect(jsonPath("releasedYear").value("2012"))
                .andExpect(jsonPath("description").value("Avengeeeeeeee"))
                .andExpect(jsonPath("rating").value("5"))
                .andDo(print());
    }

    @Test
    public void submitRatingAndTextReviewToMovieTest() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Avengers");
        movie.setDirector("Joss");
        movie.setActors("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth");
        movie.setDescription("Avengeeeeeeee");
        movie.setReleasedYear("2012");
        movieRepository.save(movie);

        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setMovieTitle("Avengers");
        ratingRequest.setStars(5);
        ratingRequest.setReview("Hate Thanos.");


        RequestBuilder rq = post("/rating")
                .content(objectMapper.writeValueAsString(ratingRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(rq).andExpect(status().isCreated())
                .andExpect(jsonPath("message").value("A rating has been added"));

        RequestBuilder get = get("/movies/Avengers");
        mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Avengers"))
                .andExpect(jsonPath("director").value("Joss"))
                .andExpect(jsonPath("actors").value("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth"))
                .andExpect(jsonPath("releasedYear").value("2012"))
                .andExpect(jsonPath("description").value("Avengeeeeeeee"))
                .andExpect(jsonPath("rating").value("5"))
                .andExpect(jsonPath("reviews", hasSize(1)))
                .andExpect(jsonPath("$.reviews[0]").value("Hate Thanos."))
                .andDo(print());
    }
}
