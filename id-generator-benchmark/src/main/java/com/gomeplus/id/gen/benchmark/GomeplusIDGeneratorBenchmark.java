package com.gomeplus.id.gen.benchmark;

import com.gomeplus.id.gen.snowflake.worker.GeneratorIdType1Worker;
import com.gomeplus.id.gen.snowflake.worker.GeneratorIdWorker;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GomeplusIDGeneratorBenchmark {


    private int threadCount;
    private int genCount;

    private static ExecutorService service = Executors.newCachedThreadPool();

    private static AtomicLong counter = new AtomicLong(0);

    public GomeplusIDGeneratorBenchmark(int genCount, int threadCount) {
        this.genCount = genCount;
        this.threadCount = threadCount;
    }


    public static void main(String[] args) throws InterruptedException {
        int threadCount = 20, genCount = 1000;

        try {
            threadCount = Integer.parseInt(args[0]);
            genCount = Integer.parseInt(args[1]);
        } catch (Exception e) {
            //skip this error
        }

        GomeplusIDGeneratorBenchmark benchmark = new GomeplusIDGeneratorBenchmark(genCount, threadCount);

        benchmark.benchmark();

    }

    public void benchmark() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        final CountDownLatch countDownLatch = new CountDownLatch(this.threadCount);
        final Map<String,Object> map = new ConcurrentHashMap<>();

        final int genCount = this.genCount;
        final GeneratorIdWorker generator  = new GeneratorIdType1Worker(1);
        stopWatch.start();


        for (int i = 0; i < this.threadCount; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < genCount; i++) {
                       long id =  generator.nextId(1);
                        counter.getAndIncrement();
                        System.out.println(id);
                    }
                    countDownLatch.countDown();
                }
            });
        }


        countDownLatch.await();
        
        stopWatch.stop();
        service.shutdown();
        System.out.println("threadCount:" + threadCount + ", genCount:" + genCount);
        System.out.println("total count:" +counter.get());

        System.out.println("time:" + stopWatch);
        System.out.println("speed:" + genCount * threadCount / (stopWatch.getTime() / 1000.0) +"/sec");

    }


}
