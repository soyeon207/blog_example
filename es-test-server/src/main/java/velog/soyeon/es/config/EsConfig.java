package velog.soyeon.es.config;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
@RequiredArgsConstructor
public class EsConfig extends AbstractElasticsearchConfiguration {

    private final EsProperties esProperties;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        // https://discuss.elastic.co/t/localhost-nodename-nor-servname-provided-or-not-known/186173/11
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(esProperties.httpHost()));
        return client;
    }

}
