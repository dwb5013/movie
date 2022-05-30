package com.dingweibing.interview.service;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.repository.MovieRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieQueryServiceImpl implements MovieQueryService {
    private final MovieRepository movieRepository;
    private final Long queryServiceMaxResultSize;

    @Inject
    public MovieQueryServiceImpl(final MovieRepository movieRepository, final Long queryServiceMaxResultSize) {
        this.movieRepository = movieRepository;
        this.queryServiceMaxResultSize = queryServiceMaxResultSize;
    }


    @Override
    public List<Movie> getMoviesByGenre(final String genres, final Pageable pageable) {
        return null;
    }

    @Override
    public List<Movie> getMovieByTitle(final String title, final Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Movie> getMovieById(final String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Long getCount() {
        return movieRepository.count();
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> result = new LinkedList<>();
        movieRepository.findAll(Pageable.ofSize(1000)).forEach(result::add);

        return result;
    }
}
