package com.galvanize.GMDB.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private String title;
    private String director;
    private String actors;
    private String releasedYear;
    private String description;
    private int rating;
}
