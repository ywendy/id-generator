package com.gomeplus.id.gen.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tony
 * @version 0.1.0
 */
public class TestLogbackLevel {

    public static final Logger LOGGER = LoggerFactory.getLogger(TestLogbackLevel.class);

    @Test
    public void testLogbackLevel() {
        LOGGER.info("logback this is info ");
        LOGGER.debug("logback this is debug ");
        LOGGER.warn("logback this is warn ");
        LOGGER.error("logback this is error ");
        LOGGER.trace("logback this is trace ");
    }
}
