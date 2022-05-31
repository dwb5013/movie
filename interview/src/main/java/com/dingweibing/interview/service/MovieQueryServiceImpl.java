package com.dingweibing.interview.service;

import com.dingweibing.interview.repository.config.QueryServiceConfig;
import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.repository.MovieRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieQueryServiceImpl implements MovieQueryService {
    private final MovieRepository movieRepository;
    private final QueryServiceConfig queryServiceConfig;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Inject
    public MovieQueryServiceImpl(final MovieRepository movieRepository, final QueryServiceConfig queryServiceConfig, final ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.movieRepository = movieRepository;
        this.queryServiceConfig = queryServiceConfig;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
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
    public List<Movie> getMovies(PageRequest pageRequest) {
        validationPageRequest(pageRequest);
        List<Movie> result = new LinkedList<>();
        movieRepository.findAll(pageRequest).forEach(result::add);
        return result;
    }

    @Override
    public List<Movie> getMoviesForFullTextSearch(final String text, final PageRequest pageRequest) {
        validationPageRequest(pageRequest);
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(text, "title", "cast", "genres"))
                .withPageable(pageRequest)
                .build();
        SearchHits<Movie> movies = elasticsearchRestTemplate.search(build, Movie.class, IndexCoordinates.of("movies"));
        return movies.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public boolean isValidPageRequest(PageRequest pageRequest) {
        return pageRequest.getPageSize() <= queryServiceConfig.maxPageSize
                && pageRequest.getPageSize() >= 0
                && pageRequest.getPageNumber() <= queryServiceConfig.maxPage
                && pageRequest.getPageNumber() >= 0;

    }

    private void validationPageRequest(PageRequest pageRequest) {
        if (!isValidPageRequest(pageRequest)) {
            throw new IllegalArgumentException(pageRequest.toString());
        }
    }
}
