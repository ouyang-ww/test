package com.ou.learn.work.exception;

/**
 * Created by weouyang on 26/01/2018.
 */
public class NoParamTypeException extends RuntimeException {

    public NoParamTypeException() {
        super();
    }

    public NoParamTypeException(String s) {
        super(s);
    }
}
