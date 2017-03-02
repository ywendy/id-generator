package com.gomeplus.id.gen.service;

/**
 * @author Tony
 * @version 0.1.0
 */
public class InitializationParameter {

    public InitializationParameter(long dataCenterId, long serverId, long workerId) {
        this.dataCenterId = dataCenterId;
        this.serverId = serverId;
        this.workerId = workerId;
    }

    public InitializationParameter(long serverId) {
       this(0,serverId,serverId);
    }

    public InitializationParameter(long dataCenterId, long workerId) {

        this(0,workerId,workerId);
    }

    private long serverId;
    private long dataCenterId;
    private long workerId;

    public long getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }
}
