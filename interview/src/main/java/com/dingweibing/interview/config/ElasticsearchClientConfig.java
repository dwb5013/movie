package com.dingweibing.interview.config;

import com.dingweibing.interview.model.Movie;
import com.dingweibing.interview.service.ConfigService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.dingweibing.interview.repository")
@ComponentScan(basePackages = {"com.dingweibing.interview"})
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

    // Todo シードデータの投入する処理はProdコードと分離することが必要
    @Value("classpath:movies.json")
    private Resource moviesData;

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

    // 試作のため、仮データでを使う
    @Bean
    public List<Movie> initData() {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON array to list of books
            List<Movie> movies = Arrays.asList(mapper.readValue(moviesData.getFile(), Movie[].class));
            System.out.println(movies.size());
            return movies;
        } catch (Exception ex) {
            throw new RuntimeException("error");
        }
    }
}
