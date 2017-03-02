package com.gomeplus.id.gen.dubbo.benchmark;

import com.alibaba.dubbo.rpc.benchmark.AbstractClientRunnable;
import com.alibaba.dubbo.rpc.benchmark.ServiceFactory;
import com.gomeplus.id.gen.api.GomeplusIDGeneratorApi;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GomeplusIDGeneratorApiBenchmark extends AbstractClientRunnable {

    public GomeplusIDGeneratorApiBenchmark(String targetIP, int targetPort, int clientNums, int rpcTimeout,
                                           CyclicBarrier barrier, CountDownLatch latch, long startTime, long endTime) {
        super(targetIP, targetPort, clientNums, rpcTimeout, barrier, latch, startTime, endTime);
    }

    @Override
    public List<long[]> getResults() {
        return super.getResults();
    }

    @Override
    public Object invoke(ServiceFactory serviceFactory) {
        GomeplusIDGeneratorApi generatorApi = (GomeplusIDGeneratorApi) serviceFactory.get(GomeplusIDGeneratorApi.class);
        return generatorApi.getId("rebate",1);
    }
}
