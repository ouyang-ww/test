package com.ou.learn.base.sys;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by weouyang on 19/05/2017.
 */
public class In {

    /**
     * 测试System.in功能
     */
    public static String testSystemIn() {
        InputStream in = System.in;
        StringBuffer sb = new StringBuffer();
        int bs_length = 10;
        int index = 0;
        byte[] bs = new byte[bs_length];
        try {
            for(;;){
                bs = new byte[bs_length];
                int re = in.read(bs,index,bs_length);
                sb.append(new String(bs).trim());
                if(re < bs_length) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
