package com.dingweibing.user.service;

import com.dingweibing.user.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieQueryService {
    Optional<Movie> getMovieById(final String id);

    Optional<List<Movie>> getMovieByIds(final List<String> id);
}
