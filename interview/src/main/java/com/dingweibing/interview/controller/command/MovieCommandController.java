package com.dingweibing.interview.controller.command;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.service.MovieCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieCommandController {
    @Autowired
    private List<Movie> initData;
    private MovieCommandService movieCommandService;

    public MovieCommandController(MovieCommandService movieCommandService) {
        this.movieCommandService = movieCommandService;
    }

    @GetMapping("/init")
    public void initData() {
        movieCommandService.initData(initData);
    }
}
