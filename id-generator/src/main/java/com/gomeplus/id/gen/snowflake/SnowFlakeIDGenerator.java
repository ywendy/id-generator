package com.gomeplus.id.gen.snowflake;

/**
 * snowflake 生成id.
 *
 * @author Tony
 * @version 0.1.0
 */
public class SnowFlakeIDGenerator {

    private IDWorker idWorker;

    public SnowFlakeIDGenerator(long dataCenterId, long workerId) {

        idWorker = new IDWorker(workerId, dataCenterId);
    }


    public long getId() {
        return idWorker.nextId();
    }


}
