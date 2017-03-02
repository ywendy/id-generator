import com.gomeplus.id.gen.common.utils.DecodeIDUtil;

/**
 * @author Tony
 * @version 0.1.0
 */
public class Test {

    public static void main(String[] args) {
        String s = "11111111111111111111111111111111111111111";
        System.out.println(((1L<<41)-1)/(3600*24*365*1000l));

        System.out.println((1L<<10)-1);
        System.out.println(Integer.toBinaryString(1023));
        System.out.println((1<<12)-1);

        System.out.println(Long.toBinaryString(1288834974657L).length());

        System.out.println(1|2);

        System.out.println(Long.toBinaryString( -1L ^ (-1L << 12)));

        System.out.println(2l&(-1L ^ (-1L << 12)));

        System.out.println(1L<<8);

        System.out.println( -1L ^ (-1L << 5L));


        long re = 1526577206338619433l;


        //181982184212
        //181982184212
        System.out.println(re >>> 23);

        System.out.println((re & 0x7fffff) >>>0x12);
        System.out.println((re & (-1L ^ (-1L << 0x12))) >>>0xA);
        System.out.println(re & 0x3FF );
        System.out.println("-------------------------------");

      /*  long[] longs = DecodeIDUtil.decodeGomeplusId(re);

        for (int i = 0; i < longs.length; i++) {
            System.out.println(longs[i]);
        }*/


        System.out.println(-1L ^ (-1L << 23));

        System.out.println(-1L ^ (-1L << 0x12));



        System.out.println("-----------------------------------------------------");



        long id = 763312891385479177l;
       // System.out.println(-1L ^ (-1L << 12));

        System.out.println(id >>> 0x16 );//timestamp

        // System.out.println(-1L ^ (-1L << 22 ));
        System.out.println((id & 0x3fffff) >>> 0x11);

        // System.out.println(-1L ^ (-1L << 17 ));
        System.out.println((id & 0x1ffff) >>> 0xc);
        System.out.println(id & 0xfff ); //seq


        System.out.println("------------------------------------");

        long[] longs1 = DecodeIDUtil.decodeSnowFlakeId(id);
        for (int i = 0; i < longs1.length; i++) {

            System.out.println(longs1[i]);
        }


        System.out.println("----------------");
        System.out.println(-1L ^ (-1L<<10l));
        System.out.println(-1L ^ (-1L<<8l));
        System.out.println(-1L ^ (-1L<<18l));


        System.out.println(Long.toBinaryString(System.currentTimeMillis()/1000).length());




        //TODO insert 性能测试  select
        //TODO seq bit 调大
        //TODO sec (看数据测试)

        //TODO 实现客户端

        //TODO


        System.out.println(-1^(-1<<23l));
        System.out.println(-1^(-1<<18l));
        System.out.println(-1^(-1<<13l));
        System.out.println(-1^(-1<<5l));
        System.out.println("------------------------------");
        System.out.println(-1^(-1<<13l));
        System.out.println(-1^(-1<<10l));












    }
}
