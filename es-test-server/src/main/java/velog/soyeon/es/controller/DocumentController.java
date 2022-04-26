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

    @GetMapping("/search")
    public List<String> getSearch() throws IOException {
        return documentService.getSearch();
    }

    @PostMapping
    public IndexResponse createDocument() throws IOException {
        return documentService.createDocument();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDocument(@PathVariable String id) throws IOException {
       return documentService.getDocument(id);
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteDocument(@PathVariable String id) throws IOException {
        return documentService.deleteDocument(id);
    }

    @PutMapping("/{id}/script")
    public UpdateResponse updateDocumentByScript(@PathVariable String id) throws IOException {
        return documentService.updateDocumentByScript(id);
    }

    @PutMapping("/{id}")
    public UpdateResponse updateDocument(@PathVariable String id) throws IOException {
        return documentService.updateDocument(id);
    }

    @PutMapping("/{id}/upsert")
    public UpdateResponse upsertDocument(@PathVariable String id) throws IOException {
        return documentService.upsertDocument(id);
    }

    @PostMapping("/bulk")
    public BulkResponse createDocumentBulk() throws IOException {
        return documentService.createDocumentBulk();
    }

}
