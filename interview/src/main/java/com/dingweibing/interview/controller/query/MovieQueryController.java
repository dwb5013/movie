package com.dingweibing.interview.controller.query;

import com.dingweibing.interview.service.MovieQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class MovieQueryController {
    private MovieQueryService movieQueryService;

    @Inject
    public MovieQueryController(MovieQueryService movieQueryService) {
        this.movieQueryService = movieQueryService;
    }

    @GetMapping("/count")
    public Long getCount() {
        return movieQueryService.getCount();
    }
}
