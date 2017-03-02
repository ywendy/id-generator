package com.gomeplus.id.gen.common.utils;


import org.junit.Test;

/**
 * @author Tony
 * @version 0.1.0
 */
public class DecodeIDUtilTest {

    @Test
    public void testDecodeSnowFlakeId() throws Exception {
        long[] ids = DecodeIDUtil.decodeSnowFlakeId(763312891385479174L);
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
    }

    @Test
    public void testDecodeGomeplusId() throws Exception {
       /* long[] ids = DecodeIDUtil.decodeGomeplusId(1527223195239710831L);
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }*/
    }
}