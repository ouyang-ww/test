package com.ou.learn.base.regex;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Pattern;

/**
 * Created by weouyang on 17/08/2017.
 */
public class Test {
    public static void main(String[] args) {
        testPattern();

    }
    public static void testPattern() {
        String content = "17,13,2";
        String pattern = "[^,]+";
        System.out.println(Pattern.matches(pattern,content));
    }
}
