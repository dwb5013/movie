package com.dingweibing.interview.service;

import com.dingweibing.interview.model.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieQueryService {
    List<Movie> getMoviesByGenre(final String genres, final Pageable pageable);

    List<Movie> getMovieByTitle(final String title, final Pageable pageable);

    Long getCount();
}
