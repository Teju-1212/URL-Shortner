package com.example.urlshortener.service;

import com.example.urlshortener.model.ShortUrl;
import com.example.urlshortener.repo.UrlRepository;
import com.example.urlshortener.util.Base62;
import com.example.urlshortener.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UrlService {
    private final UrlRepository repo;
    private final SnowflakeIdGenerator idGen;

    @Value("${shortener.domain:http://localhost:8080}")
    private String domain;

    public UrlService(UrlRepository repo) {
        this.repo = repo;
        // nodeId can be injected via properties in production; using 1 for local run
        this.idGen = new SnowflakeIdGenerator(1L);
    }

    public String createShortUrl(String longUrl, String customAlias) {
        if (customAlias != null && !customAlias.isBlank()) {
            if (repo.exists(customAlias)) throw new IllegalArgumentException("Alias already exists");
            ShortUrl s = new ShortUrl(customAlias, longUrl, Instant.now());
            repo.save(s);
            return buildUrl(customAlias);
        }
        long id = idGen.nextId();
        String shortKey = Base62.encode(id);
        ShortUrl s = new ShortUrl(shortKey, longUrl, Instant.now());
        repo.save(s);
        return buildUrl(shortKey);
    }

    public String resolve(String shortKey) {
        return repo.findByKey(shortKey).map(ShortUrl::getLongUrl).orElse(null);
    }

    private String buildUrl(String shortKey) {
        if (domain.endsWith("/")) return domain + shortKey;
        return domain + "/" + shortKey;
    }
}
