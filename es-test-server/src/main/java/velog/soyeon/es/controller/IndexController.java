package velog.soyeon.es.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import velog.soyeon.es.service.IndexService;

import java.io.IOException;

@Slf4j
@RequestMapping("/api/index")
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    /**
     * sync(동기) 방식으로 인덱스를 생성하는 API
     *
     * @return 인덱스 생성 성공 여부
     * @throws IOException
     */
    @PostMapping("/sync")
    public boolean createIndexSync() {
        return indexService.createIndexSync();
    }
    /**
     * async(비동기) 방식으로 인덱스를 생성하는 API
     */
    @PostMapping("/async")
    public void createIndexAsync() {
        indexService.createIndexAsync();
    }

    /**
     * 인덱스를 삭제하는 API
     *
     * @return
     */
    @DeleteMapping
    public boolean deleteIndex() {
        return indexService.deleteIndex();
    }

    /**
     * 인덱스를 오픈하는 API
     *
     * @return
     */
    @PutMapping("/open")
    public boolean openIndex() {
        return indexService.openIndex();
    }

    /**
     * 인덱스를 닫는 API
     *
     * @return
     */
    @PutMapping("/close")
    public boolean closeIndex() {
        return indexService.closeIndex();
    }

}
