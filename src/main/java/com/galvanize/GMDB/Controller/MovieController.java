package com.galvanize.GMDB.Controller;

import com.galvanize.GMDB.Exception.MovieNotFoundException;
import com.galvanize.GMDB.Service.MovieService;
import com.galvanize.GMDB.request.MovieRequest;
import com.galvanize.GMDB.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping(value={"/movies", "/movies/{movieName}"})
    public ResponseEntity<?> getMovie(@PathVariable(required = false) String movieName) throws MovieNotFoundException {
        return movieName == null ?
                ResponseEntity.ok(movieService.getMovies())
                :
                ResponseEntity.ok(movieService.getMovieByName(movieName))
                ;
    }

    @PostMapping("/movies")
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest movie){
        return new ResponseEntity<MovieResponse>(movieService.create(movie), HttpStatus.CREATED);
    }
}
