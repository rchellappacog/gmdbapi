package com.galvanize.GMDB.Exception;

import com.galvanize.GMDB.response.MovieResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<?> handleMovieNotFoundException(MovieNotFoundException exception) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setMessage(exception.getMessage());
        return  new ResponseEntity<>(movieResponse, HttpStatus.NOT_FOUND);
    }
}
