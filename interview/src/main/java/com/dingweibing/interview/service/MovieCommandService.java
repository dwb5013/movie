package com.dingweibing.interview.service;

import com.dingweibing.interview.model.Movie;

import java.util.List;

public interface MovieCommandService {
    void createMovieIndexBulk(final List<Movie> movies);

    void createMovieIndex(final Movie movie);

    void initData(final List<Movie> movies);
}
