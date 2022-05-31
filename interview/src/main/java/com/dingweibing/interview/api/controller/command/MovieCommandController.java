package com.dingweibing.interview.api.controller.command;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.service.MovieCommandService;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class MovieCommandController {
    private final MovieCommandService movieCommandService;
    private final List<Movie> initData;

    @Inject
    public MovieCommandController(final MovieCommandService movieCommandService, final List<Movie> initData) {
        this.movieCommandService = movieCommandService;
        this.initData = initData;
        initData();
    }

    private void initData() {
        if (this.initData != null && !this.initData.isEmpty()) {
            movieCommandService.initData(this.initData);
        }
    }
}
