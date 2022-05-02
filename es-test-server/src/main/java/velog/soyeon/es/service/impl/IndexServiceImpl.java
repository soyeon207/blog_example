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

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final EsProperties esProperties;
    private final RestHighLevelClient client;

    @Override
    public boolean createIndexSync() {
        CreateIndexRequest request = new CreateIndexRequest(esProperties.getTestIndexName());
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0));

        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (Exception e) {
            log.error("인덱스 생성에 실패했습니다. 원인 : " + e.getMessage());
            return false;
        }

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
                log.error("인덱스 생성에 실패했습니다. 원인 : " + e.getMessage());
            }
        };

        client.indices().createAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public boolean deleteIndex() {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(esProperties.getTestIndexName());
            return client.indices().delete(request, RequestOptions.DEFAULT).isAcknowledged();
        } catch (Exception e) {
            log.error("인덱스 삭제에 실패했습니다. 원인 : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean openIndex() {
        try {
            OpenIndexRequest request = new OpenIndexRequest(esProperties.getTestIndexName());
            return client.indices().open(request, RequestOptions.DEFAULT).isAcknowledged();
        } catch (Exception e) {
            log.error("인덱스 오픈을 실패했습니다. 원인 : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean closeIndex() {
        try {
            CloseIndexRequest request = new CloseIndexRequest(esProperties.getTestIndexName());
            return client.indices().close(request, RequestOptions.DEFAULT).isAcknowledged();
        } catch (Exception e) {
            log.error("인덱스 닫기를 실패했습니다. 원인 : " + e.getMessage());
            return false;
        }
    }

}

