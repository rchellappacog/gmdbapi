package com.galvanize.GMDB.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int numberOfStars;

    private String review;

    @ManyToOne
    @JoinColumn(name="movie_id")
    @JsonIgnore
    private Movie movie;
}
