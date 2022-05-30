package com.dingweibing.interview.repository;

import com.dingweibing.interview.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
}
