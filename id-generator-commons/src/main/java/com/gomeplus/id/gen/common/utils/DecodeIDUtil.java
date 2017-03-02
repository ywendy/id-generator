package com.gomeplus.id.gen.common.utils;

import java.util.Date;

/**
 * 对已经生成的id进行反解，获得到时间戳，seq，业务信息等.
 *
 * @author Tony
 * @version 0.1.0
 */
public final class DecodeIDUtil {

    /**
     * 反解出snowflake 生成的id.
     *
     * @param snowflakeId snowflake 生成的id.
     * @return the length of 4 long array.
     * <p/>
     * <p>[0]:timestamp</p>
     * <p>[1]:dataCenterId</p>
     * <p>[2]:workerId</p>
     * <p>[3]:seq</p>
     */
    public static long[] decodeSnowFlakeId(long snowflakeId) {

        long timestamp = snowflakeId >>> 0x16;
        long dataCenterId = (snowflakeId & 0x3fffff) >>> 0x11;
        long workerId = (snowflakeId & 0x1ffff) >>> 0xc;
        long seq = snowflakeId & 0xfff;
        long[] longs = new long[4];
        longs[0] = timestamp + Constants.SNOWFLAKE_EPOCH;
        longs[1] = dataCenterId;
        longs[2] = workerId;
        longs[3] = seq;
        return longs;

    }


    /**
     * 反解gomeplusIDWorker生成的id.
     * type:#see api doc
     * <p/>
     * <pre>
     *
     * 返回的long数组.
     *     +-------------------------------------------------------------------
     *     |        0       |       1       |       2        |      3       |
     *     +--------------------------------+--------------------------------
     *     |    timestamp  |    serverId    |   serviceId   |   sequence    |
     *     +-----------------------------------------------------------------
     *
     * </pre>
     *
     * @param gomeplusId
     * @param type
     * @return
     */
    public static long[] decodeGomeplusIdByType(long gomeplusId, int type) {
        switch (type) {
            case 0:
                return decodeType0(gomeplusId);
            case 1:
                return decodeType1(gomeplusId);
            case 2:
                return decodeType2(gomeplusId);
            default:
                throw new RuntimeException("没有找到相应的反解类型，请传入正确的类型");
        }
    }

    private static long[] decodeType0(long gomeplusId) {


        long timestamp = gomeplusId >>> 0x17;
        long serverId = (gomeplusId & 0x7fffff) >>> 0x12;
        long serviceId = (gomeplusId & 0x3ffff) >>> 0xa;
        long seq = gomeplusId & 0x3ff;
        long[] longs = new long[4];
        longs[0] = timestamp + Constants.GOMEPLUS_EPOCH;
        longs[1] = serverId;
        longs[2] = serviceId;
        longs[3] = seq;
        return longs;
    }

    private static long[] decodeType1(long gomeplusId) {

        long timestamp = gomeplusId >>> 0x17;
        long serverId = (gomeplusId & 0x7fffff) >>> 0x12;
        long serviceId = (gomeplusId & 0x3ffff) >>> 0xd;
        long seq = gomeplusId & 0x1fff;
        long[] longs = new long[4];
        longs[0] = timestamp + Constants.GOMEPLUS_EPOCH;
        longs[1] = serverId;
        longs[2] = serviceId;
        longs[3] = seq;
        return longs;
    }

    private static long[] decodeType2(long gomeplusId) {

        long timestamp = gomeplusId >>> 0x17;
        long seq = (gomeplusId & 0x7fffff) >>> 0xa;
        long serverId = (gomeplusId & 0x3ff) >>> 0x5;
        long serviceId = gomeplusId & 0x1f;
        long[] longs = new long[4];
        longs[0] = timestamp + Constants.GOMEPLUS_EPOCH;
        longs[1] = serverId;
        longs[2] = serviceId;
        longs[3] = seq;
        return longs;
    }
    private static long[] decodeType3(long gomeplusId) {
        long timestamp = gomeplusId >>> 33;
        long serverId = (gomeplusId & (-1 ^ (-1 << 31))) >>> 28;
        long serviceId = (gomeplusId & (-1^(-1<<36))) >>>23 ;
        long seq = gomeplusId & (-1^(-1<<23));
        long[] longs = new long[4];
        longs[0] = timestamp + 1_288_834_974L;
        longs[1] = serverId;
        longs[2] = serviceId;
        longs[3] = seq;
        return longs;
    }




}
