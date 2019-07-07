package com.luo.order.util;

import java.util.Random;

public class KeyUtils {

    /**
     * 生成唯一订单id
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random r = new Random();
       Integer num =  r.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
