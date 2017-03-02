package com.gomeplus.id.gen.snowflake.worker;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GeneratorIdType3WorkerTest {


    @Test
    public void testNextId(){

        Set<Long> set = new HashSet<>();
        GeneratorIdType3Worker idType3Worker = new GeneratorIdType3Worker(1);
        for (int i = 0; i < 100; i++) {
            long id = idType3Worker.nextId(2);
            set.add(id);
        }


        System.out.println(set.size());
    }

}