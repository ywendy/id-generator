package com.gomeplus.id.gen.snowflake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ------------------------------------------------
 * |   1   |   41    |    5    |    5    |   12  |
 * ------------------------------------------------
 * <p/>
 * <p/>
 * <p>1:符号位不使用</p>
 * <p>41：时间戳（ms）((1L<<41)-1)/(3600*24*365*1000l) = 69 年</p>
 * <p>5： dataCenterId</p>
 * <p>5:  workerId</p> (1<<10)-1 = 1023 表示1023 个数
 * <p>12：序列号sequence (1<<12)-1 = 4095 毫秒内自增数量</p>
 *
 * @author Tony
 * @version 0.1.0
 */
public class IDWorker {
    protected static final Logger LOG = LoggerFactory.getLogger(IDWorker.class);

    private long workerId;
    private long dataCenterId;
    private long sequence = 0L;

    private long twepoch = 1288834974657L;//时间戳基数（越大，生成的id越小）

    private long workerIdBits = 5L;
    private long dataCenterIdBits = 5L;
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long dataCenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public IDWorker(long workerId, long dataCenterId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",
                    maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        LOG.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, " +
                        "sequence bits %d, workerid %d", timestampLeftShift, dataCenterIdBits, workerIdBits,
                sequenceBits,
                workerId));
    }

    public synchronized long nextId() {
        //获取时间戳
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            LOG.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d " +
                    "milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId <<
                workerIdShift) | sequence;
    }

    /**
     * 如果时间延后了，需要等待时间超过最后更新时间，等待
     *
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
