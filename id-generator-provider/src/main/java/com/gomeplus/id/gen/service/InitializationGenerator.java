package com.gomeplus.id.gen.service;

import com.gomeplus.id.gen.service.holder.PropertiesHolder;
import com.gomeplus.id.gen.snowflake.GeneratorFactory;

/**
 * 初始化参数信息.
 *
 * @author Tony
 * @version 0.1.0
 */
public class InitializationGenerator {


    public InitializationGenerator(long serverId) {
        init(serverId);
    }


    public void init(long serverId) {


        InitializationParameter parameter = new InitializationParameter(serverId);
        PropertiesHolder.setParameter(parameter);
        GeneratorFactory.initFactory(serverId);


    }
}
