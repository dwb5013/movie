package com.dingweibing.user.service;

import com.dingweibing.user.model.Movie;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieQueryServiceImpl implements MovieQueryService {
    private final RestTemplate restTemplate;

    @Inject
    public MovieQueryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<Movie> getMovieById(String id) {
        Movie movie = null;
        URI uri = UriComponentsBuilder.newInstance()
                .host("movieapi")
                .port(8080)
                .scheme("http")
                .pathSegment("movies", id)
                .build()
                .toUri();
        try {
            movie = restTemplate.getForObject(uri, Movie.class);
        } catch (HttpStatusCodeException exception) {
            return Optional.empty();
        }
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<List<Movie>> getMovieByIds(List<String> id) {
        List<Optional<Movie>> response = id.stream().map(this::getMovieById).collect(Collectors.toList());
        List<Movie> result = response.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        return Optional.of(result);
    }
}
