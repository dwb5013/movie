package com.dingweibing.interview.config;

import com.dingweibing.interview.service.ConfigService;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class QueryServiceConfig {

    private final ConfigService configService;
    public final Integer maxResultSize;

    @Inject
    public QueryServiceConfig(ConfigService configService) {
        this.configService = configService;
        this.maxResultSize = configService.getQueryServiceMaxResultSize();
    }
}
