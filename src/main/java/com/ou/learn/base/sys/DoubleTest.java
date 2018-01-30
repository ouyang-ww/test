package com.ou.learn.base.sys;

/**
 * Created by weouyang on 17/10/2017.
 */
public class DoubleTest {

    public static void main(String[] args) {
        testDouble1();
    }

    public static void testEmptyString2Double() {
        String s = "";
        System.out.println(Double.valueOf(s));
    }

    public static void testString2Double() {
        String s = "0.0";
        System.out.println(Double.valueOf(s));
    }

    public static void testDouble1() {
        Object o = 3600D;
        System.out.println((o instanceof Double ? true:false) + " result:"+o);
    }
}
