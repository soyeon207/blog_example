package velog.soyeon.es.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Setter
@ConfigurationProperties(prefix = "elasticsearch")
public class EsProperties {

    private String host;
    private int port;
    private Indices indecies;

    public HttpHost httpHost() {
        return new HttpHost(host, port, "http");
    }

    public String getStudentIndexName() {
        return Optional.ofNullable(indecies).map(Indices::getStudentsIndexName).orElse(null);
    }

    public String getTestIndexName() {
        return Optional.ofNullable(indecies).map(Indices::getTestIndexName).orElse(null);
    }

    @Getter
    @Setter
    public static class Indices {
        String studentsIndexName;
        String testIndexName;
    }

}
