package com.dingweibing.interview.service;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.repository.MovieRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieQueryServiceImpl implements MovieQueryService {
    private MovieRepository movieRepository;

    public MovieQueryServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public List<Movie> getMoviesByGenre(String genres, Pageable pageable) {
        return null;
    }

    @Override
    public List<Movie> getMovieByTitle(String title, Pageable pageable) {
        return null;
    }

    @Override
    public Long getCount() {
        return movieRepository.count();
    }
}
