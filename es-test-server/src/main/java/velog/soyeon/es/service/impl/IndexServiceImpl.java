package velog.soyeon.es.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CloseIndexRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.springframework.stereotype.Service;
import velog.soyeon.es.config.EsProperties;
import velog.soyeon.es.service.IndexService;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final EsProperties esProperties;
    private final RestHighLevelClient client;

    @Override
    public boolean createIndexSync() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(esProperties.getTestIndexName());
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0));

        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    @Override
    public void createIndexAsync() {
        CreateIndexRequest request = new CreateIndexRequest(esProperties.getTestIndexName());
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0));

        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                System.out.println("성공!!");
            }

            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }
        };

        client.indices().createAsync(request,RequestOptions.DEFAULT, listener);
    }

    @Override
    public boolean deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(esProperties.getTestIndexName());
        return client.indices().delete(request, RequestOptions.DEFAULT).isAcknowledged();
    }

    @Override
    public boolean openIndex() throws IOException {
        OpenIndexRequest request = new OpenIndexRequest(esProperties.getTestIndexName());
        return client.indices().open(request, RequestOptions.DEFAULT).isAcknowledged();
    }

    @Override
    public boolean closeIndex() throws IOException {
        CloseIndexRequest request = new CloseIndexRequest(esProperties.getTestIndexName());
        return client.indices().close(request, RequestOptions.DEFAULT).isAcknowledged();
    }

}
