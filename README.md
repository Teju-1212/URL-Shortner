# URL Shortener - Runnable Base Project

This is a minimal, runnable Spring Boot project for a URL shortener service.

## Run locally

Requirements: JDK 17+, Maven

1. Build:

```bash
mvn clean package
```

2. Run:

```bash
java -jar target/urlshortener-0.0.1-SNAPSHOT.jar
```

3. Create a short URL (curl):

```bash
curl -X POST -H "Content-Type: application/json" -d '{"url":"https://example.com"}' http://localhost:8080/api/v1/shorten
```

4. Use the returned short URL in the Location to be redirected.

## Notes & How to scale later

- Repository currently uses an in-memory `ConcurrentHashMap` implementation. Replace `InMemoryUrlRepository` with a Cassandra, DynamoDB, or RDBMS-backed implementation by implementing the `UrlRepository` interface.
- `SnowflakeIdGenerator` is included; in production supply nodeId via configuration or use a segment allocator.
- Add Redis caching, Kafka event publishing, and metrics (Micrometer + Prometheus) as next steps.
