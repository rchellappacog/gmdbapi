package com.galvanize.GMDB.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {
    private String movieTitle;
    private String stars;
    private String review;
}
