package com.galvanize.GMDB.Service;

import com.galvanize.GMDB.Entity.Movie;
import com.galvanize.GMDB.Entity.MovieRating;
import com.galvanize.GMDB.Exception.MovieNotFoundException;
import com.galvanize.GMDB.repository.MovieRepository;
import com.galvanize.GMDB.repository.RatingRepository;
import com.galvanize.GMDB.request.RatingRequest;
import com.galvanize.GMDB.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public ResponseEntity<?> addRating(RatingRequest ratingRequest) throws MovieNotFoundException {
        Movie savedMovie = movieRepository.findByTitle(ratingRequest.getMovieTitle());
        if(savedMovie==null)
            throw new MovieNotFoundException("Movie does not exist");

        MovieRating movieRating = new MovieRating();
        movieRating.setMovie(savedMovie);
        movieRating.setNumberOfStars(ratingRequest.getStars());
        movieRating.setReview(ratingRequest.getReview());

        ratingRepository.save(movieRating);
        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("A rating has been added");
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }
}
