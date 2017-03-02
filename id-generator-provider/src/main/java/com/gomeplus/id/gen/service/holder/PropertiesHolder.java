package com.gomeplus.id.gen.service.holder;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.gomeplus.id.gen.service.GomeplusIDGeneratorService;
import com.gomeplus.id.gen.service.InitializationParameter;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tony
 * @version 0.1.0
 */
public final class PropertiesHolder {

    public static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHolder.class);

    private static InitializationParameter parameter;

    public static int getServerId() {
        try {
            return Integer.parseInt(Long.toString(parameter.getServerId()));
        } catch (Exception e) {
            LOGGER.error(" get server id error,please set server id in conf.properties ", e);
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
            return Long.valueOf(ConfigUtils.getProperty(getKey(business), "0"));
        } catch (Exception e) {
            //skip this exception.
        }

        return 0;

    }

    private static String getKey(String business) {
        return "business." + business;
    }

    public static InitializationParameter getParameter() {
        return parameter;
    }

    public static void setParameter(InitializationParameter parameter) {
        PropertiesHolder.parameter = parameter;
    }
}
