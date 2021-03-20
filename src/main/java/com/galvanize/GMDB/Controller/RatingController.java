package com.galvanize.GMDB.Controller;

import com.galvanize.GMDB.Exception.MovieNotFoundException;
import com.galvanize.GMDB.Service.RatingService;
import com.galvanize.GMDB.request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> addRatingToMovie(@RequestBody RatingRequest ratingRequest) throws MovieNotFoundException {
        return ratingService.addRating(ratingRequest);
    }
}
