package com.ou.learn.base.sys.test_class;

/**
 * Created by weouyang on 03/11/2017.
 */
public class TestEnum {

    public static void main(String[] args) {
        getEnumClassName();
    }


    /**
     * enum 中的枚举元素，相当于enum class的实例化对象，所以无法通过JAVA找到其所说的enum
     * */
    public static void getEnumClassName() {
        String name = "JAVA";
        try {
            Class stringClass = Class.forName(name);
            System.out.println(stringClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
