package com.galvanize.GMDB.Service;

import com.galvanize.GMDB.Entity.Movie;
import com.galvanize.GMDB.Exception.MovieNotFoundException;
import com.galvanize.GMDB.repository.MovieRepository;
import com.galvanize.GMDB.request.MovieRequest;
import com.galvanize.GMDB.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieResponse create(MovieRequest movie) {
        Movie newMovie = new Movie();
        newMovie.setTitle(movie.getTitle());
        newMovie.setActors(movie.getActors());
        newMovie.setDescription(movie.getDescription());
        newMovie.setReleasedYear(movie.getReleasedYear());
        movieRepository.save(newMovie);
        return new MovieResponse("Movie has been created.");
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByName(String movieName) throws MovieNotFoundException {
        movieRepository.findByTitle(movieName);
        Movie savedMovie = movieRepository.findByTitle(movieName);
        if (savedMovie == null) throw new MovieNotFoundException("Movie not available");

        return savedMovie;
    }
}
