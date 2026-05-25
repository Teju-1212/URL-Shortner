package com.example.urlshortener.repo;

import com.example.urlshortener.model.ShortUrl;
import java.util.Optional;

public interface UrlRepository {
    Optional<ShortUrl> findByKey(String shortKey);
    void save(ShortUrl shortUrl);
    boolean exists(String shortKey);
}
