package com.dingweibing.movie.service;

import com.dingweibing.movie.model.Movie;
import com.dingweibing.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MovieCommandServiceImpl implements MovieCommandService {
    private final MovieRepository movieRepository;

    @Inject
    public MovieCommandServiceImpl(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void createMovieIndexBulk(final List<Movie> movies) {

    }

    @Override
    public void createMovieIndex(final Movie movie) {

    }

    @Override
    public void initData(final List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
}
