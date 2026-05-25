package com.example.urlshortener.repo;

import com.example.urlshortener.model.ShortUrl;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUrlRepository implements UrlRepository {
    private final Map<String, ShortUrl> store = new ConcurrentHashMap<>();

    @Override
    public Optional<ShortUrl> findByKey(String shortKey) {
        return Optional.ofNullable(store.get(shortKey));
    }

    @Override
    public void save(ShortUrl shortUrl) {
        store.put(shortUrl.getShortKey(), shortUrl);
    }

    @Override
    public boolean exists(String shortKey) {
        return store.containsKey(shortKey);
    }
}
