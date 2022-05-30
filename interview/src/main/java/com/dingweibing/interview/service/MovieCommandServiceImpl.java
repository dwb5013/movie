package com.dingweibing.interview.service;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MovieCommandServiceImpl implements MovieCommandService {
    private MovieRepository movieRepository;

    @Inject
    public MovieCommandServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void createMovieIndexBulk(List<Movie> movies) {

    }

    @Override
    public void createMovieIndex(Movie movie) {

    }

    @Override
    public void initData(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }
}
