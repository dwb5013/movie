package com.dingweibing.interview.controller.query;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.service.MovieQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieQueryController {
    private final MovieQueryService movieQueryService;

    @Inject
    public MovieQueryController(final MovieQueryService movieQueryService) {
        this.movieQueryService = movieQueryService;
    }

    @GetMapping("/count")
    public Long getCount() {
        return movieQueryService.getCount();
    }

    @GetMapping("/movies/{id}")
    public Optional<Movie> getMovie(@PathVariable final String id) {
        return movieQueryService.getMovieById(id);
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieQueryService.getAllMovies();
    }
}
