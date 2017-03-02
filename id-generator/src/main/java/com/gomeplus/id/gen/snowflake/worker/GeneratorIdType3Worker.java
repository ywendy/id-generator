package com.gomeplus.id.gen.snowflake.worker;

import com.gomeplus.id.gen.common.utils.Constants;
import com.gomeplus.id.gen.snowflake.GeneratorUtil;
import com.gomeplus.id.gen.snowflake.Validator;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * -----------------------------------------------------------
 * |    31bit     |   5bit    |   5bit     |   23bit     |
 * ----------------------------------------------------------
 * |    sec      |  serverId |  serviceId | seq         |
 * ---------------------------------------------------------------
 *
 * @author Tony
 * @version 0.1.0
 */
public class GeneratorIdType3Worker extends GeneratorIdWorker {

    public GeneratorIdType3Worker(long serverId) {
        this.serverId = serverId;
    }


    @Override
    public synchronized long nextId(long serviceId) {

        long timestamp = GeneratorUtil.genTimestampSec();

        Validator.validateTimestamp(timestamp, lastTimestamp);

        timestamp = getTimestamp(timestamp, lastTimestamp);
        lastTimestamp = timestamp;
        return ((timestamp - 1_288_834_974L) << 33) | (this.serverId << 28) | (serviceId << 23) | sequence;
    }

    @Override
    protected long getTimestamp(long timestamp, long lastTimestamp) {

        if (lastTimestamp == timestamp) {

            sequence = (sequence + 1) & 0x7FFFFF;
            if (sequence == 0) {
                timestamp = GeneratorUtil.tilNextSeconds(lastTimestamp);
            }

        } else {
            sequence = 0L;
        }
        return timestamp;

    }
}
