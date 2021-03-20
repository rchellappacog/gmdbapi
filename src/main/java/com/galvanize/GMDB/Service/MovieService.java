package com.galvanize.GMDB.Service;

import com.galvanize.GMDB.Pojo.Movie;
import com.galvanize.GMDB.request.MovieRequest;
import com.galvanize.GMDB.response.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    public static List<Movie> movieList;

    MovieService() {
        movieList = new ArrayList<>();
    }

    public MovieResponse create(MovieRequest movie) {
        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        movieList.add(newMovie);
        return new MovieResponse("Movie has been created.");
    }

    public List<Movie> getMovies() {
        return movieList;
    }
}
