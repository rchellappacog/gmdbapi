package com.galvanize.GMDB.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String director;
    private String actors;
    private String releasedYear;
    private String description;
    @OneToMany(mappedBy = "movie")
    private List<MovieRating> rating;
}