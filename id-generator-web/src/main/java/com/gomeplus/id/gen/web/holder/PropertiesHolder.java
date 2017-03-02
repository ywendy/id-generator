package com.gomeplus.id.gen.web.holder;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Tony
 * @version 0.1.0
 */
public class PropertiesHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHolder.class);

    private static Configuration configuration;

    public static void initProperties(Configuration configuration) {

        PropertiesHolder.configuration = configuration;
    }


    public static Configuration getConfiguration() {
        return configuration;
    }

    public static int getServerId() {
        try {
            return configuration.getInt("server.id", 0);
        } catch (Exception e) {
            LOGGER.error("get server id error,please set server id in conf.properties ", e);
        }
        return 0;
    }

    /**
     * 获取到business 对应的id.
     *
     * @param business 业务类型.
     * @return 如果business 无效，则获取到的值为0
     */
    public static long getServiceIdByBusiness(String business) {
        try {
            return configuration.getInt(getKey(business), 0);
        } catch (Exception e) {
            //skip this exception.
        }

        return 0;

    }


    private static String getKey(String business) {
        return "business." + business;
    }


}
