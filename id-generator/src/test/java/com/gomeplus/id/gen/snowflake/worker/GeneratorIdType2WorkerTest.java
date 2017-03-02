package com.gomeplus.id.gen.snowflake.worker;

import com.gomeplus.id.gen.common.utils.DecodeIDUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GeneratorIdType2WorkerTest {

    private static final long SERVER_ID = 1;
    private static final long SERVICE_ID = 2;
    private static final int TYPE = 2;
    private static final long ZERO = 0L;
    private static final long MAX_DIFF = 5L;
    private static final int NUM = 100;
    private static final int MAX_NUM = 2048;
    private static final int MIN_NUM = 0;


    @Test
    public void testNextId() throws Exception {

        GeneratorIdWorker idWorker = new GeneratorIdType2Worker(SERVER_ID);

        long id = idWorker.nextId(SERVICE_ID);

        long[] decodeId = DecodeIDUtil.decodeGomeplusIdByType(id, TYPE);

        assertTrue(decodeId[0] < Long.MAX_VALUE);
        assertTrue(decodeId[0] > ZERO);
        assertTrue(decodeId[1] == SERVER_ID);
        assertTrue(decodeId[2] == SERVICE_ID);
        assertTrue(decodeId[3] >= ZERO);

        long currentMillis = System.currentTimeMillis();
        long diff = currentMillis - decodeId[0];
        assertTrue(diff <= MAX_DIFF);
        assertTrue(id <= Long.MAX_VALUE);
    }

    @Test
    public void testGetScopeId() {
        GeneratorIdType2Worker idWorker = new GeneratorIdType2Worker(SERVER_ID);
        long[] result = idWorker.nextScopeId(SERVICE_ID, NUM);
        assertTrue(result.length == NUM);
    }

    @Test(expected = RuntimeException.class)
    public void testGetScopeId_1() {
        GeneratorIdType2Worker idWorker = new GeneratorIdType2Worker(SERVER_ID);
        idWorker.nextScopeId(SERVICE_ID, MAX_NUM + 1);
    }

    @Test(expected = RuntimeException.class)
    public void testGetScopeId_2() {
        GeneratorIdType2Worker idWorker = new GeneratorIdType2Worker(SERVER_ID);
        idWorker.nextScopeId(SERVICE_ID, MIN_NUM-1);
    }
    @Test
    public void testGetScopeId_3() {
        GeneratorIdType2Worker idWorker = new GeneratorIdType2Worker(SERVER_ID);
        long[] result = idWorker.nextScopeId(SERVICE_ID, MIN_NUM);
        assertTrue(result.length == MIN_NUM);
    }


}