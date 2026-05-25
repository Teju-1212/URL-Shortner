package com.example.urlshortener.api;

import com.example.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RedirectController {
    private final UrlService service;

    public RedirectController(UrlService service) { this.service = service; }

    @GetMapping("/{shortKey}")
    public void redirect(@PathVariable String shortKey, HttpServletResponse http) throws Exception {
        String longUrl = service.resolve(shortKey);
        if (longUrl == null) {
            http.sendError(404);
            return;
        }
        http.setStatus(302);
        http.setHeader("Location", longUrl);
    }
}
