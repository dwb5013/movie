package com.dingweibing.movie.api.controller.command;

import com.dingweibing.movie.model.Movie;
import com.dingweibing.movie.service.MovieCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;

@RestController
public class MovieCommandController {

    // Todo シードデータの投入する処理はProdコードと分離することが必要
    // 試作のため、仮データでを使う
    private final ResourceLoader resourceLoader;
    private final MovieCommandService movieCommandService;

    @Inject
    public MovieCommandController(final MovieCommandService movieCommandService, final ResourceLoader resourceLoader) {
        this.movieCommandService = movieCommandService;
        this.resourceLoader = resourceLoader;
        {
            try {
                Resource resource = resourceLoader.getResource("classpath:movies.json");
                ObjectMapper mapper = new ObjectMapper();
                movieCommandService.initData(Arrays.asList(mapper.readValue(resource.getInputStream(), Movie[].class)));
            } catch (Exception ex) {
                throw new IllegalArgumentException(ex);
            }

        }
    }
}
