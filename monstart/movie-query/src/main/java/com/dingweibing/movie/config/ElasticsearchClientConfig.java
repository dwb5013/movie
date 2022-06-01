package com.dingweibing.movie.config;

import com.dingweibing.movie.service.ConfigService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.inject.Inject;
import javax.net.ssl.SSLContext;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.dingweibing.movie.repository")
@ComponentScan(basePackages = {"com.dingweibing.movie"})
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {
    // Todo 本来は各マイクロサービスのログを統一で収集することが必要ですが、試作のため、今回は省略する
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchClientConfig.class);
    private final ConfigService configService;

    @Inject
    public ElasticsearchClientConfig(ConfigService configService) {
        this.configService = configService;
    }

    // ES7からES8にマイグレーションすると、非交換性の問題が多いため、今のところ、ES7はまだ主流です
    // 今回の試作として、ES7を使います
    // ES8から廃止されるAPIを使っているため、ワーニングが出る
    @SuppressWarnings("deprecation")
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        try {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(configService.getEsUsername(), configService.getEsPassword()));
            // Todo 試作のため、SSLを回避する、Prod環境であれば、要注意
            SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true);
            final SSLContext sslContext = sslBuilder.build();
            return new RestHighLevelClient(RestClient
                    .builder(new HttpHost(configService.getEsHostname(), configService.getEsPort(), configService.getEsConnectionSchema()))
                    .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                            .setSSLContext(sslContext)
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .setDefaultCredentialsProvider(credentialsProvider))
                    .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                            .setConnectTimeout(configService.getEsConnectTimeoutLength())
                            .setSocketTimeout(configService.getEsSocketTimeoutLength())));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
