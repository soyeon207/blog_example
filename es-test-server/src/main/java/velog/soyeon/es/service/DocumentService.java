package velog.soyeon.es.service;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DocumentService {

    List<String> getSearch() throws IOException;

    IndexResponse createDocument() throws IOException;

    Map<String, Object> getDocument(String id) throws IOException;

    DeleteResponse deleteDocument(String id) throws IOException;

    UpdateResponse updateDocumentByScript(String id) throws IOException;

    UpdateResponse updateDocument(String id) throws IOException;

    UpdateResponse upsertDocument(String id) throws IOException;

    BulkResponse createDocumentBulk() throws IOException;

}
