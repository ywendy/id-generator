package com.gomeplus.id.gen.consumer;

import com.gomeplus.id.gen.api.GomeplusIDGeneratorApi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tony
 * @version 0.1.0
 */
public class GeneratorConsumer {


    private GomeplusIDGeneratorApi generatorAPI;


    private Map<Long, Long> keyMap = new HashMap<>();

    public GomeplusIDGeneratorApi getGeneratorAPI() {
        return generatorAPI;
    }

    public void setGeneratorAPI(GomeplusIDGeneratorApi generatorAPI) {
        this.generatorAPI = generatorAPI;
    }

    public void start() {
        String str = "insert into id_table values ";
        String str2 = "";

        for (int i = 0; i < 1000000; i++) {
            long id = this.generatorAPI.getId("rebate", 1);

            keyMap.put(id, 123L);

            str2+=id+",";
            if (i%1000 == 0 && i!=0 ){
                System.out.println(str2);
                str2 = "";
            }
            //str2 += "(" + id + "),";
            //System.out.println("id=>"+id);
        }

        System.out.println(keyMap.size());
        //System.out.println(str + str2.substring(0, str2.length() - 1));


    }
}
