package com.gomeplus.id.gen.snowflake.worker;

import com.gomeplus.id.gen.common.utils.Constants;
import com.gomeplus.id.gen.snowflake.GeneratorUtil;
import com.gomeplus.id.gen.snowflake.Validator;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GeneratorIdType2Worker extends GeneratorIdWorker {


    public GeneratorIdType2Worker(long serverId) {

        this.serverId = serverId;
    }


    @Override
    public synchronized long nextId(long serviceId) {
        long timestamp = GeneratorUtil.genTimestampMills();
        Validator.validateTimestamp(timestamp, lastTimestamp);
        timestamp = getTimestamp(timestamp, lastTimestamp);

        lastTimestamp = timestamp;

        return ((timestamp - Constants.GOMEPLUS_EPOCH) << Constants.GOMEPLUS_TIMESTAMP_SHIFT) | sequence << 10 | (this
                .serverId << Constants.GOMEPLUS_SERVER_ID_TYPE2_SHIFT) | serviceId;

    }

    /**
     * 获取毫秒内一段id 列表.
     *
     * @param serviceId 业务线id .
     * @param num       num >0 and num <=2048
     * @return the id array.
     * @throws RuntimeException .
     */
    public synchronized long[] nextScopeId(long serviceId, int num) {


        if (num < 0 || num > Constants.TYPE2_SCOPE_NUM) {
            throw new RuntimeException("the num[" + num + "] is invalid,must between 1 and 2048 ");
        }

        long[] result = new long[num];

        for (int i = 0; i < num; i++) {
            result[i] = nextId(serviceId);
        }


        return result;

    }

    @Override
    protected long getTimestamp(long timestamp, long lastTimestamp) {

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & Constants.GOMEPLUS_TYPE2_SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = GeneratorUtil.tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        return timestamp;
    }
}