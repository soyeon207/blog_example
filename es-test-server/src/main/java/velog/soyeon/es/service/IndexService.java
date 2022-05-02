package velog.soyeon.es.service;

public interface IndexService {

    boolean createIndexSync();

    void createIndexAsync();

    boolean deleteIndex();

    boolean openIndex();

    boolean closeIndex();

}