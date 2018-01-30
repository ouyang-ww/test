package com.ou.learn.base.sys.test_class;

/**
 * Created by weouyang on 03/11/2017.
 */
public enum StringEnum {
    JAVA("java"),
    C("c++"),
    PYTHON("pyhton2.7"),
    SCALA("spark"),
    GO("go");

    public String description;
    private StringEnum(String desc) {
        description = desc;
    }
}
