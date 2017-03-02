package com.gomeplus.id.gen.common.utils;

/**
 * 常量类，用来配置一些生成id需要的固定的常量.
 *
 * @author Tony
 * @version 0.1.0
 * @since 1.7
 */
public final class Constants {


    /**
     * private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
     * private long sequenceMask = -1L ^ (-1L << sequenceBits);
     */
    public static final long GOMEPLUS_MAX_SERVER_ID = 0x1f;
    //1288834974657L
    public static final long GOMEPLUS_EPOCH = 1_288_834_974_657L;

    public static final long GOMEPLUS_SEQUENCE_MASK = 0x3ff;
    public static final long GOMEPLUS_TYPE1_SEQUENCE_MASK = 0x1fff;
    public static final long GOMEPLUS_TYPE2_SEQUENCE_MASK = 0x7FFFFF;


    public static final long GOMEPLUS_TIMESTAMP_SHIFT = 0x17;

    public static final long GOMEPLUS_SERVER_ID_SHIFT = 0x12;
    public static final long GOMEPLUS_SERVER_ID_TYPE2_SHIFT = 0x5;

    public static final long GOMEPLUS_SERVICE_ID_SHIFT = 0xa;
    public static final long GOMEPLUS_TYPE1_SERVICE_ID_SHIFT = 0xd;


    //1288834974657L
    public static final long SNOWFLAKE_EPOCH = 1_288_834_974_657L;

    //范围获取id时的最大值
    public static final int TYPE2_SCOPE_NUM = 2048;


    public static final String LOG_SESSION_ID = "logSessionId";


}
