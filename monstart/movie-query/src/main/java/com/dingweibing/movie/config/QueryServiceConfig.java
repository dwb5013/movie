package com.dingweibing.movie.config;

import com.dingweibing.movie.service.ConfigService;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class QueryServiceConfig {

    private final ConfigService configService;
    public final Integer maxPageSize;
    public final Integer maxPage;

    @Inject
    public QueryServiceConfig(ConfigService configService) {
        this.configService = configService;
        this.maxPageSize = configService.getQueryServiceMaxPageSize();
        this.maxPage = configService.getQueryServiceMaxPage();
    }
}
