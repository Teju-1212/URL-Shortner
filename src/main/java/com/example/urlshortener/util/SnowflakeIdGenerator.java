package com.example.urlshortener.util;

public class SnowflakeIdGenerator {
    private final long epoch = 1609459200000L; // Jan 1 2021
    private final long nodeId;
    private final long nodeBits = 10L;
    private final long seqBits = 12L;
    private final long maxNodeId = ~(-1L << nodeBits);
    private final long maxSeq = ~(-1L << seqBits);
    private long lastTs = -1L;
    private long sequence = 0L;

    public SnowflakeIdGenerator(long nodeId) {
        if (nodeId < 0 || nodeId > maxNodeId) throw new IllegalArgumentException("Invalid nodeId");
        this.nodeId = nodeId;
    }

    public synchronized long nextId() {
        long ts = System.currentTimeMillis();
        if (ts < lastTs) throw new RuntimeException("Clock moved backwards");
        if (ts == lastTs) {
            sequence = (sequence + 1) & maxSeq;
            if (sequence == 0) {
                while (ts <= lastTs) ts = System.currentTimeMillis();
            }
        } else {
            sequence = 0;
        }
        lastTs = ts;
        long id = ((ts - epoch) << (nodeBits + seqBits)) | (nodeId << seqBits) | sequence;
        return id;
    }
}
