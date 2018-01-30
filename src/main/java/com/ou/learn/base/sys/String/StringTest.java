package com.ou.learn.base.sys.String;

/**
 * Created by weouyang on 30/09/2017.
 */
public class StringTest {

    public static void main(String[] args) {
        testSplit();
    }

    public static void testStringSplit() {
        String testString = "ab~b~fg~ddd,ss";
        String[] resultArray = testString.split("~",-1);
        for (String s:resultArray) {
            System.out.println("less than zero --"+s);
        }

        resultArray = testString.split("~",0);
        for (String s:resultArray) {
            System.out.println("equals zero --"+s);
        }

        resultArray = testString.split("~",1);
        for (String s:resultArray) {
            System.out.println("greater than zero --"+s);
        }
    }

    public static void testSplit() {
        String testString = "ab|b~fg|d*dd,ss";
        String[] resultArray = testString.split("\\|");
        for (String s:resultArray) {
            System.out.println("test | --"+s);
        }

        resultArray = testString.split("\\*");
        for (String s:resultArray) {
            System.out.println("test * --"+s);
        }

        resultArray = testString.split("[d][d]");
        for (String s:resultArray) {
            System.out.println("test regex --"+s);
        }
    }

    public static void testString2Int() {
        try {

            Object s1 = new String("123");
            System.out.println(Integer.parseInt((String)s1));

            Object s = new String("");
            System.out.println(Integer.parseInt((String)s));

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
