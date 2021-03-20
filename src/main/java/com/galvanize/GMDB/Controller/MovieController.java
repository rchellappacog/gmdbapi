package com.galvanize.GMDB.Controller;

import com.galvanize.GMDB.Service.MovieService;
import com.galvanize.GMDB.request.MovieRequest;
import com.galvanize.GMDB.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/movies")

public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getMovie(){
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest movie){
        return new ResponseEntity<MovieResponse>(movieService.create(movie), HttpStatus.CREATED);
    }
}
