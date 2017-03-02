package com.gomeplus.id.gen.web.holder;

import com.gomeplus.id.gen.snowflake.GeneratorFactory;
import com.gomeplus.id.gen.snowflake.worker.GeneratorIdWorker;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GeneratorHolder {


    static {
         GeneratorFactory.initFactory(Long.parseLong(PropertiesHolder.getServerId()+""));
    }

    public static GeneratorIdWorker getGenerator(int type) {
        return GeneratorFactory.getIDWorker(type);
    }

    public static long getId(String business,int type){
       return getGenerator(type).nextId(PropertiesHolder.getServiceIdByBusiness(business));
    }
}
