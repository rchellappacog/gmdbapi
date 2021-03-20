package com.galvanize.GMDB.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.GMDB.Entity.Movie;
import com.galvanize.GMDB.Service.MovieService;
import com.galvanize.GMDB.repository.MovieRepository;
import com.galvanize.GMDB.request.MovieRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    public void init() {
        movieRepository.deleteAll();
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
        MovieRequest mr = new MovieRequest();
        mr.setTitle("Aliens");
        mr.setDirector("Joss");
        mr.setActors("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth");
        mr.setDescription("Avengeeeeeeee");
        mr.setReleasedYear("2012");
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
                .andExpect(jsonPath("$[0].title").value("Aliens"))
                .andDo(print());
    }

    @Test
    public void getMovieWhenAMovieExistTest() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Avengers");
        movie.setDirector("Joss");
        movie.setActors("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth");
        movie.setDescription("Avengeeeeeeee");
        movie.setReleasedYear("2012");
        movieRepository.save(movie);

        RequestBuilder go = get("/movies/Avengers");
        mockMvc.perform(go)
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Avengers"))
                .andExpect(jsonPath("director").value("Joss"))
                .andExpect(jsonPath("actors").value("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth"))
                .andExpect(jsonPath("releasedYear").value("2012"))
                .andExpect(jsonPath("description").value("Avengeeeeeeee"))
                .andDo(print());
    }

    @Test
    public void getMessageWhenMovieNotExistTest() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Avengers");
        movie.setDirector("Joss");
        movie.setActors("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth");
        movie.setDescription("Avengeeeeeeee");
        movie.setReleasedYear("2012");
        movieRepository.save(movie);

        RequestBuilder go = get("/movies/AnythingElse");
        mockMvc.perform(go)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("message").value("Movie not available"))
                .andDo(print());
    }

    /**
     * Given the GBDB has many movies
     * When I visit GMDB movies
     * Then I should see that movie in GMDB movies
     */

    @Test
    public void getMovieWhenManyMovieExistTest() throws Exception {
        Movie avengers = new Movie();
        avengers.setTitle("Avengers");
        Movie wonderwoman = new Movie();
        wonderwoman.setTitle("Wonder Woman");
        Movie justiceLeague = new Movie();
        justiceLeague.setTitle("Justice League");
        Movie starWars = new Movie();
        starWars.setTitle("Star Wars");
        movieRepository.saveAll(Arrays.asList(avengers, wonderwoman, justiceLeague, starWars));

        RequestBuilder go = get("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(go)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].title").value("Avengers"))
                .andExpect(jsonPath("$[1].title").value("Wonder Woman"))
                .andExpect(jsonPath("$[2].title").value("Justice League"))
                .andExpect(jsonPath("$[3].title").value("Star Wars"))
                .andDo(print());
    }

}