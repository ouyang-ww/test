package com.ou.learn.base.String;

import java.util.Arrays;

/**
 * Created by weouyang on 27/10/2017.
 */
public class StringArray {
    public static void main(String[] args) {
        testNewDoubleStringArray();
    }

    public static void testNewDoubleStringArray() {
        String[][] stringGroup = new String[][]{new String[]{"1-1","1-2"},new String[]{"2-1","2-2"}};
        System.out.println(stringGroup);
//        Arrays.stream(stringGroup).filter(group -> group.length > 1).
//            forEach(group -> Arrays.stream(group).forEach(s -> System.out.println(s)));
//        System.out.println("-------------------");
//        for (String [] group: stringGroup) {
//            for (String s:group){
//                System.out.println(s);
//            }
//        }
    }
}
