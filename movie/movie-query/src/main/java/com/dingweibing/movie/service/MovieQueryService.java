package com.dingweibing.movie.service;

import com.dingweibing.movie.model.Movie;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface MovieQueryService {
    Optional<Movie> getMovieById(final String id);

    Long getCount();

    List<Movie> getMovies(PageRequest pageRequest);

    List<Movie> getMoviesForFullTextSearch(final String text, final PageRequest pageRequest);

    boolean isValidPageRequest(PageRequest pageRequest);
}
