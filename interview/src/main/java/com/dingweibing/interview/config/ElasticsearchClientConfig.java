package com.dingweibing.interview.config;

import com.dingweibing.interview.model.Movie;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.dingweibing.interview.repository")
@ComponentScan(basePackages = {"com.dingweibing.interview"})
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {
    @Value("classpath:movies.json")
    private Resource moviesData;

    // Elasticsearch 8.0 Migrationする必要
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        try {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("elastic", "BobWithAlice"));
            // 試作のため、SSLを回避する、Prod環境であれば、要注意
            SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true);
            final SSLContext sslContext = sslBuilder.build();
            return new RestHighLevelClient(RestClient
                    // 試作のため、明文化にハードコーディングにする、Prod環境であれば、要注意
                    .builder(new HttpHost("localhost", 9200, "https"))
                    .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                            .setSSLContext(sslContext)
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .setDefaultCredentialsProvider(credentialsProvider))
                    .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                            .setConnectTimeout(5000)
                            .setSocketTimeout(120000)));
        } catch (Exception e) {
            // 試作のため、ログや特定のエラー処理しない、Prod環境であれば、要注意
            throw new RuntimeException(e);
        }
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
