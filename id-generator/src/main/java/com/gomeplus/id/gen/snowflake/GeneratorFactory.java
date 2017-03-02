package com.gomeplus.id.gen.snowflake;

import com.gomeplus.id.gen.snowflake.worker.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tony
 * @version 0.1.0
 */
public final class GeneratorFactory {

    private static Map<Integer, GeneratorIdWorker> factory;


    private static boolean initFlag =false;



    public static void initFactory(long serverId) {
        if (!initFlag) {
            Validator.validateServerId(serverId);

            factory = new ConcurrentHashMap<>();
            try {
                factory.put(0, new GeneratorIdTypeWorker(serverId));
                factory.put(1, new GeneratorIdType1Worker(serverId));
                factory.put(2, new GeneratorIdType2Worker(serverId));
                factory.put(3, new GeneratorIdType3Worker(serverId));
            } catch (Exception e) {
                throw new RuntimeException("init id worker occur error ", e);
            }

            initFlag = true;
        }



    }


    public static GeneratorIdWorker getIDWorker(Integer type) {

        try {
            return factory.get(type);
        } catch (Exception e) {
            throw new RuntimeException("get id worker error!", e);
        }

    }


}
