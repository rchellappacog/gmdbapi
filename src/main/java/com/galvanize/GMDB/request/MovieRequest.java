package com.galvanize.GMDB.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    private String title;
    private String director;
    private String actors;
    private String releasedYear;
    private String description;
    private String rating;
}
