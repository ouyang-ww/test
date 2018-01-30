package com.ou.learn.base.String;

/**
 * Created by weouyang on 09/08/2017.
 */
public class Test {
    public static void main(String[] args) {
        stringOut();
    }

    /**
     * test:字符串输出long型
     */
    public static void stringOut() {
        Object l = 234l;
        System.out.println(String.valueOf((Long) l));
        Object ll = null;
        System.out.println(String.valueOf((Long) ll));
    }
}
