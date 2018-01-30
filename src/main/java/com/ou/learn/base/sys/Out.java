package com.ou.learn.base.sys;

/**
 * Created by weouyang on 19/05/2017.
 */
public class Out {
    /**
     * 测试System.out功能
     */
    public static void testSystemOut() {
        System.out.println("hello ouyang!");
        //append 也会输出到控制台
        System.out.append("11");
        System.out.append(In.testSystemIn());
    }

    public static void main(String[] args) throws Exception {
        testSystemOut();
    }
}

