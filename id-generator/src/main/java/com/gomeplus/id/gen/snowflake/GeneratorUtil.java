package com.gomeplus.id.gen.snowflake;

import com.gomeplus.id.gen.common.utils.Constants;

/**
 * @author Tony
 * @version 0.1.0
 */
public final class GeneratorUtil {


    public static long genTimestampMills() {
        return System.currentTimeMillis();
    }

    public static long genTimestampSec() {
        return System.currentTimeMillis() / 1000;
    }


    public static long tilNextMillis(long lastTimestampMillis) {
        long timestamp = genTimestampMills();
        while (timestamp <= lastTimestampMillis) {
            timestamp = genTimestampMills();
        }
        return timestamp;

    }

    public static long tilNextSeconds(long lastTimestampSeconds) {
        long timestamp = genTimestampSec();
        while (timestamp <= lastTimestampSeconds) {
            timestamp = genTimestampSec();
        }
        return timestamp;
    }







}
