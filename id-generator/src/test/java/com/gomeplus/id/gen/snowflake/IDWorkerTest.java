package com.gomeplus.id.gen.snowflake;

import com.gomeplus.id.gen.common.utils.DecodeIDUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Tony
 * @version 0.1.0
 */
public class IDWorkerTest {

    @Test
    public void testNextId() throws Exception {


        IDWorker idWorker = new IDWorker(0,1);

        long id = idWorker.nextId();

        long[] result = DecodeIDUtil.decodeSnowFlakeId(id);

        long timestamp = result[0];
        long dataCenterId = result[1];
        long workerId = result[2];
        long seq = result[3];

        assertEquals(1, dataCenterId);
        assertTrue(workerId == 0);
        assertTrue(Long.toBinaryString(seq).length() <= 12);
        assertTrue(Long.toBinaryString(timestamp).length() <= 41);


    }
}