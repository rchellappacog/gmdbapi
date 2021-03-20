package com.galvanize.GMDB.Pojo;

import lombok.Data;

@Data
public class Movie {
    private String title;
    private String director;
    private String actors;
    private String releasedYear;
    private String description;
    private String rating;
}