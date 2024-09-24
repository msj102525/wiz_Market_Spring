package com.jyes.www.utils;

import java.util.UUID;

public class SnowflakeIdGenerator {

    private static final long EPOCH = 1626297600000L; // 2021-07-15 00:00:00 UTC
    private static final long NODE_ID_BITS = 10L;
    private static final long SEQUENCE_BITS = 12L;

    private static long nodeId;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;

    static {
        // UUID로 nodeId 설정
        nodeId = UUID.randomUUID().getLeastSignificantBits() & ((1L << NODE_ID_BITS) - 1);
    }

    public synchronized static String generateUniqueFilename() {
        long currentTimestamp = System.currentTimeMillis();

        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id.");
        }

        if (lastTimestamp == currentTimestamp) {
            sequence = (sequence + 1) & ((1L << SEQUENCE_BITS) - 1);
            if (sequence == 0) {
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        long snowflakeId = ((currentTimestamp - EPOCH) << (NODE_ID_BITS + SEQUENCE_BITS)) | (nodeId << SEQUENCE_BITS) | sequence;

        // Snowflake ID를 파일 이름으로 사용
        String uniqueFilename = Long.toString(snowflakeId);

        return uniqueFilename;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        String uniqueFilename = generateUniqueFilename();
        System.out.println("Unique Filename: " + uniqueFilename);
    }
}