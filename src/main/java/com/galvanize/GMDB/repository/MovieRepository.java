package com.galvanize.GMDB.repository;

import com.galvanize.GMDB.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

    Movie findByTitle(String title);
}
