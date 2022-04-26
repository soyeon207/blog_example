package velog.soyeon.es.service;

import java.io.IOException;

public interface IndexService {

    boolean createIndexSync() throws IOException;

    void createIndexAsync();

    boolean deleteIndex() throws IOException;

    boolean openIndex() throws IOException;

    boolean closeIndex() throws IOException;

}
