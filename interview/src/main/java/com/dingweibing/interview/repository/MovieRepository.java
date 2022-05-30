package com.dingweibing.interview.repository;

import com.dingweibing.interview.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
}
