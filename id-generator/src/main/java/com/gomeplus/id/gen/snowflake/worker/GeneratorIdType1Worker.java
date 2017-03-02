package com.gomeplus.id.gen.snowflake.worker;

import com.gomeplus.id.gen.common.utils.Constants;
import com.gomeplus.id.gen.snowflake.GeneratorUtil;
import com.gomeplus.id.gen.snowflake.Validator;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GeneratorIdType1Worker extends GeneratorIdWorker {


    public GeneratorIdType1Worker(long serverId) {
        this.serverId = serverId;
    }

    @Override
    public synchronized long nextId(long serviceId) {
        long timestamp = GeneratorUtil.genTimestampMills();

        Validator.validateTimestamp(timestamp, lastTimestamp);
        getTimestamp(timestamp, lastTimestamp);

        lastTimestamp = timestamp;

        return ((timestamp - Constants.GOMEPLUS_EPOCH) << Constants.GOMEPLUS_TIMESTAMP_SHIFT) | (this.serverId <<
                Constants.GOMEPLUS_SERVER_ID_SHIFT) | (serviceId <<
                Constants.GOMEPLUS_TYPE1_SERVICE_ID_SHIFT) | sequence;

    }


    @Override
    protected long getTimestamp(long timestamp, long lastTimestamp) {
        if (lastTimestamp == timestamp) {

            sequence = (sequence + 1) & Constants.GOMEPLUS_TYPE1_SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = GeneratorUtil.tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0L;
        }
        return timestamp;
    }
}
