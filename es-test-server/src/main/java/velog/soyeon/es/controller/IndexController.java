package velog.soyeon.es.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.*;
import velog.soyeon.es.service.IndexService;

import java.io.IOException;

@Slf4j
@RequestMapping("/api/index")
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    @PostMapping("/sync")
    public boolean createIndexSync() throws IOException {
       return indexService.createIndexSync();
    }

    @PostMapping("/async")
    public void createIndexAsync() {
        indexService.createIndexAsync();
    }

    @DeleteMapping
    public boolean deleteIndex() throws IOException {
        return indexService.deleteIndex();
    }

    @PutMapping("/open")
    public boolean openIndex() throws IOException {
        return indexService.openIndex();
    }

    @PutMapping("/close")
    public boolean closeIndex() throws IOException {
        return indexService.closeIndex();
    }

}
