package com.dingweibing.interview.service;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void createMovieIndexBulk(final List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    public void createMovieIndex(final Movie movie) {
        movieRepository.save(movie);
    }
}
