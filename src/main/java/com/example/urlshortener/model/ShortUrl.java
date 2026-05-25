package com.example.urlshortener.model;

import java.time.Instant;

public class ShortUrl {
    private String shortKey;
    private String longUrl;
    private Instant createdAt;

    public ShortUrl() {}

    public ShortUrl(String shortKey, String longUrl, Instant createdAt) {
        this.shortKey = shortKey;
        this.longUrl = longUrl;
        this.createdAt = createdAt;
    }

    public String getShortKey() { return shortKey; }
    public void setShortKey(String shortKey) { this.shortKey = shortKey; }
    public String getLongUrl() { return longUrl; }
    public void setLongUrl(String longUrl) { this.longUrl = longUrl; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
