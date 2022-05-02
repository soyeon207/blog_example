package velog.soyeon.es.controller;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.web.bind.annotation.*;
import velog.soyeon.es.service.DocumentService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    /**
     * 도큐먼트 스크립트에 따른 검색 API
     * @return
     * @throws IOException
     */
    @GetMapping("/search")
    public List<String> getSearch() throws IOException {
        return documentService.getSearch();
    }

    /**
     * 도큐먼트 생성 API
     * @return
     * @throws IOException
     */
    @PostMapping
    public IndexResponse createDocument() throws IOException {
        return documentService.createDocument();
    }

    /**
     * 아이디로 도큐먼트 검색 API
     * @param id : 도큐먼트 아이디
     * @return
     * @throws IOException
     */
    @GetMapping("/{id}")
    public Map<String, Object> getDocument(@PathVariable String id) throws IOException {
       return documentService.getDocument(id);
    }

    /**
     * 아이디로 도큐먼트 삭제 API
     * @param id : 도큐먼트 아이디
     * @return
     * @throws IOException
     */
    @DeleteMapping("/{id}")
    public DeleteResponse deleteDocument(@PathVariable String id) throws IOException {
        return documentService.deleteDocument(id);
    }

    /**
     * script 를 사용한 도큐먼트 수정 API
     * @param id
     * @return
     * @throws IOException
     */
    @PutMapping("/{id}/script")
    public UpdateResponse updateDocumentByScript(@PathVariable String id) throws IOException {
        return documentService.updateDocumentByScript(id);
    }

    /**
     * 도큐먼트 수정 API
     * @param id
     * @return
     * @throws IOException
     */
    @PutMapping("/{id}")
    public UpdateResponse updateDocument(@PathVariable String id) throws IOException {
        return documentService.updateDocument(id);
    }

    /**
     * 도큐먼트 upsert API (도큐먼트가 있는 경우 insert, 없는 경우 update)
     * @param id
     * @return
     * @throws IOException
     */
    @PutMapping("/{id}/upsert")
    public UpdateResponse upsertDocument(@PathVariable String id) throws IOException {
        return documentService.upsertDocument(id);
    }

    /**
     * 도큐먼트 bulk insert API
     * @return
     * @throws IOException
     */
    @PostMapping("/bulk")
    public BulkResponse createDocumentBulk() throws IOException {
        return documentService.createDocumentBulk();
    }

}
