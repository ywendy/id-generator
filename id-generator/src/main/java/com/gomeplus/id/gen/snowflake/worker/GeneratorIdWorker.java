package com.gomeplus.id.gen.snowflake.worker;

/**
 * @author Tony
 * @version 0.1.0
 */
public abstract class GeneratorIdWorker {

    protected long serverId;
    protected long sequence = 0L;
    protected long lastTimestamp = -1L;


    public   abstract  long nextId(long serviceId);

    protected abstract long getTimestamp(long timestamp, long lastTimestamp);

}
