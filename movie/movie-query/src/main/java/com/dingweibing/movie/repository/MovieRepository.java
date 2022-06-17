package com.dingweibing.movie.repository;

import com.dingweibing.movie.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
}
