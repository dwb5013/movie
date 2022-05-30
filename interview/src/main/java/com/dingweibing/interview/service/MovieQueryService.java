package com.dingweibing.interview.service;

import com.dingweibing.interview.model.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieQueryService {
    List<Movie> getMoviesByGenre(final String genres, final Pageable pageable);

    List<Movie> getMovieByTitle(final String title, final Pageable pageable);

    Optional<Movie> getMovieById(final String id);

    Long getCount();

    List<Movie> getAllMovies();
}
