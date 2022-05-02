package velog.soyeon.es.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import velog.soyeon.es.config.EsProperties;
import velog.soyeon.es.service.DocumentService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final RestHighLevelClient client;
    private final EsProperties esProperties;

    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String SCORE = "score";
    private static final String CLASS = "class";
    private static final String CREATED_AT = "createdAt";

    @Override
    public List<String> getSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest(esProperties.getStudentIndexName());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 이름이 학생1, 학생22 인 도큐먼트를 찾는다.
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder
                .should(QueryBuilders.matchQuery(NAME, "학생1"))
                .should(QueryBuilders.matchQuery(NAME, "학생22"));

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchHits searchHits = client.search(searchRequest, RequestOptions.DEFAULT).getHits();
        return Optional.ofNullable(searchHits)
                .map(SearchHits::getHits)
                .map(v -> Arrays.stream(v)
                        .map(SearchHit::getSourceAsString)
                        .distinct()
                        .collect(Collectors.toList())
                ).orElse(Collections.emptyList());

        // queryDSL 로 표현했을 때
        // GET /students/_search
        //{
        //  "query": {
        //    "bool": {
        //      "should": [
        //        {
        //          "match": {
        //            "name": "학생1"
        //          }
        //        },
        //        {
        //          "match": {
        //            "name": "학생22"
        //          }
        //        }
        //      ]
        //    }
        //  }
        //}
    }

    @Override
    public IndexResponse createDocument() throws IOException {
        IndexRequest request = new IndexRequest(esProperties.getStudentIndexName());
        request.source(jsonBuilder()
                .startObject()
                .field(NAME, "소연")
                .field(AGE, 21)
                .field(SCORE, 100)
                .field(CLASS, "B")
                .endObject());
        try {
            return client.index(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                log.error("문서 생성에 실패하였습니다.");
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> getDocument(String id) throws IOException {
        GetRequest request = new GetRequest(esProperties.getStudentIndexName(), id);
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        if (getResponse.isExists()) {
            // 도큐먼트가 있는 경우
            return getResponse.getSourceAsMap();
        }
        return null;
    }

    @Override
    public DeleteResponse deleteDocument(String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(esProperties.getStudentIndexName(), id);
        return client.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Override
    public UpdateResponse updateDocumentByScript(String id) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(esProperties.getStudentIndexName(), id);

        // name 을 이름 수정으로 변경
        Map<String, Object> parameterMap = Collections.singletonMap(NAME, "이름수정");
        Script inline = new Script(ScriptType.INLINE, "painless", "ctx._source.name = params.name", parameterMap);
        // 이미 스크립트가 등록되어 있는 경우에는 ScriptType 을 Stored 로
        updateRequest.script(inline);
        // updateRequest.scriptedUpsert(true/false);
        // updateRequest.upsert(inline);

        try {
            return client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                System.out.println("업데이트 대상이 존재하지 않습니다. ");
            }
        }
        return null;
    }

    @Override
    public UpdateResponse updateDocument(String id) throws IOException {
        // name 을 이름수정으로 변경
        XContentBuilder builder = jsonBuilder()
                .startObject()
                .field(NAME, "이름수정")
                .endObject();

        UpdateRequest updateRequest = new UpdateRequest(esProperties.getStudentIndexName(), id).doc(builder);
        return client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public UpdateResponse upsertDocument(String id) throws IOException {
        IndexRequest indexRequest = new IndexRequest(esProperties.getStudentIndexName()).source(jsonBuilder().startObject().field(NAME, "소소").endObject());

        XContentBuilder xContentBuilder = jsonBuilder()
                .startObject()
                .field(CREATED_AT, new Date())
                .endObject();

        UpdateRequest updateRequest = new UpdateRequest(esProperties.getStudentIndexName(), id).doc(xContentBuilder).upsert(indexRequest);
        return client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public BulkResponse createDocumentBulk() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest(esProperties.getStudentIndexName()).source(XContentType.JSON, NAME, "테스트1"));
        request.add(new IndexRequest(esProperties.getStudentIndexName()).source(XContentType.JSON, NAME, "테스트2"));
        request.add(new IndexRequest(esProperties.getStudentIndexName()).source(XContentType.JSON, NAME, "테스트3"));
        return client.bulk(request, RequestOptions.DEFAULT);
    }

}
