package com.dingweibing.interview.api.controller.query;

import com.dingweibing.interview.api.MoviesApi;
import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.service.MovieQueryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;
import java.util.List;

@RestController
public class MovieQueryController implements MoviesApi {

    private final MovieQueryService movieQueryService;

    @Inject
    public MovieQueryController(final MovieQueryService movieQueryService) {
        this.movieQueryService = movieQueryService;
    }

    @Override
    public ResponseEntity<Movie> getMovieById(final String movieId) {
        Movie result = movieQueryService.getMovieById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Movie>> searchMovies(final String keyword, Integer page, Integer pageSize) {
        if (keyword.isEmpty()) {
            List<Movie> movies = movieQueryService.getMovies(PageRequest.of(page, pageSize));
            if (movies == null) {
                return ResponseEntity.noContent().build();
            } else {
                return new ResponseEntity<>(movies, HttpStatus.OK);
            }
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return new ResponseEntity<>(movieQueryService.getMoviesForFullTextSearch(keyword, pageRequest), HttpStatus.OK);
    }
}
