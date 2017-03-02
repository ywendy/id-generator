package com.gomeplus.id.gen.service;

import com.gomeplus.id.gen.api.GomeplusIDGeneratorApi;
import com.gomeplus.id.gen.service.holder.PropertiesHolder;
import com.gomeplus.id.gen.snowflake.GeneratorFactory;
import com.gomeplus.id.gen.snowflake.worker.GeneratorIdType2Worker;
import com.gomeplus.id.gen.snowflake.worker.GeneratorIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Tony
 * @version 0.1.0
 * @since 1.7
 */
@Service("gomeplusIDGeneratorService")
public class GomeplusIDGeneratorService implements GomeplusIDGeneratorApi {

    public static final Logger LOGGER = LoggerFactory.getLogger(GomeplusIDGeneratorService.class);


    @Override
    public long getId(String business, int type) {

        try {
            GeneratorIdWorker idWorker = GeneratorFactory.getIDWorker(type);
            if (idWorker != null) {
                return idWorker.nextId(PropertiesHolder.getServiceIdByBusiness(business));
            }
            throw new RuntimeException("generator id error,type[" + type + "] is not invalid!");
        } catch (Exception e) {
            LOGGER.error("generator id error ! business={},type={}", business, type, e);

            throw new RuntimeException("generator id error!", e);
        }
    }

    @Override
    public long[] getScopeId(String business, int type, int num) {

        if (type != 2) {
            throw new RuntimeException("暂时只支持type=2 获取一定范围的id ");
        }
        GeneratorIdType2Worker idType2Worker = (GeneratorIdType2Worker) GeneratorFactory.getIDWorker(type);
        return idType2Worker.nextScopeId(PropertiesHolder.getServiceIdByBusiness(business), num);
    }


}
