package com.galvanize.GMDB.Service;

import com.galvanize.GMDB.Entity.Movie;
import com.galvanize.GMDB.Entity.MovieRating;
import com.galvanize.GMDB.Exception.MovieNotFoundException;
import com.galvanize.GMDB.repository.MovieRepository;
import com.galvanize.GMDB.request.MovieRequest;
import com.galvanize.GMDB.response.CustomResponse;
import com.galvanize.GMDB.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public CustomResponse create(MovieRequest movie) {
        Movie newMovie = new Movie();
        newMovie.setTitle(movie.getTitle());
        newMovie.setActors(movie.getActors());
        newMovie.setDescription(movie.getDescription());
        newMovie.setReleasedYear(movie.getReleasedYear());
        movieRepository.save(newMovie);
        return new CustomResponse("Movie has been created.");
    }

    public List<MovieResponse> getMovies() {

        return movieRepository.findAll().stream()
                .map(movie->{
                    MovieResponse movieResponse = new MovieResponse();
                    movieResponse.setTitle(movie.getTitle());
                    movieResponse.setActors(movie.getActors());
                    movieResponse.setReleasedYear(movie.getReleasedYear());
                    movieResponse.setDirector(movie.getDirector());
                    movieResponse.setDescription(movie.getDescription());
                    movieResponse.setRating(calculateRating(movie));
                    return movieResponse;
                }).collect(Collectors.toList());
    }

    public MovieResponse getMovieByName(String movieName) throws MovieNotFoundException {
        movieRepository.findByTitle(movieName);
        Movie savedMovie = movieRepository.findByTitle(movieName);
        if (savedMovie == null) throw new MovieNotFoundException("Movie not available");
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTitle(savedMovie.getTitle());
        movieResponse.setActors(savedMovie.getActors());
        movieResponse.setReleasedYear(savedMovie.getReleasedYear());
        movieResponse.setDirector(savedMovie.getDirector());
        movieResponse.setDescription(savedMovie.getDescription());
        movieResponse.setRating(calculateRating(savedMovie));
        return movieResponse;
    }

    private int calculateRating(Movie movie){
        List<MovieRating> ratings = movie.getRating();
        if(ratings==null) return 0;
        return (int) ratings.stream()
                .mapToInt(MovieRating::getNumberOfStars)
                .average()
                .orElse(0);
    }
}
