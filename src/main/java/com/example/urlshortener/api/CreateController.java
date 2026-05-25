package com.example.urlshortener.api;

import com.example.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CreateController {
    private final UrlService service;

    public CreateController(UrlService service) { this.service = service; }

    public static class CreateRequest {
        public String url;
        public String customAlias;
    }

    public static class CreateResponse {
        public String shortUrl;
        public CreateResponse(String shortUrl) { this.shortUrl = shortUrl; }
    }

    @PostMapping("/shorten")
    public ResponseEntity<CreateResponse> create(@RequestBody CreateRequest req) {
        if (req == null || req.url == null || req.url.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            String shortUrl = service.createShortUrl(req.url, req.customAlias);
            return ResponseEntity.status(201).body(new CreateResponse(shortUrl));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(new CreateResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
