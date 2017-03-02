package com.gomeplus.id.gen.snowflake;

import com.gomeplus.id.gen.common.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tony
 * @version 0.1.0
 */
public final class Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);


    public static  void validateServerId(long serverId){
        if (serverId > Constants.GOMEPLUS_MAX_SERVER_ID || serverId < 0L) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    Constants.GOMEPLUS_MAX_SERVER_ID));
        }
    }


    public static void validateTimestamp(long startTimestamp,long lastTimestamp){
        if (startTimestamp < lastTimestamp) {
            LOGGER.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d " +
                    "milliseconds", lastTimestamp - startTimestamp));
        }
    }





}
