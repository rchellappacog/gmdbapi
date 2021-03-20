package com.galvanize.GMDB.repository;

import com.galvanize.GMDB.Entity.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<MovieRating, UUID> {
}
