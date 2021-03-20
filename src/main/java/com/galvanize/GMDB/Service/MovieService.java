package com.galvanize.GMDB.Service;

import com.galvanize.GMDB.request.MovieRequest;
import com.galvanize.GMDB.response.MovieResponse;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    public MovieResponse create(MovieRequest movie) {
        return new MovieResponse("Movie has been created.");
    }
}
