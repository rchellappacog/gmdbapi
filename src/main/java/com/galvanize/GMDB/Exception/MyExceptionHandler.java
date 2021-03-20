package com.galvanize.GMDB.Exception;

import com.galvanize.GMDB.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<?> handleMovieNotFoundException(MovieNotFoundException exception) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage(exception.getMessage());
        return  new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
    }
}
