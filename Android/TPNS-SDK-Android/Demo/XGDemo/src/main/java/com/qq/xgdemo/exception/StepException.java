package com.qq.xgdemo.exception;

/**
 * Created by chacewang on 2019/7/4.
 */

public class StepException extends Exception {
    public StepException(){
        super();
    }

    public StepException(String message){
        super(message);
    }

    public StepException(String message, Throwable cause){
        super(message,cause);
    }

    public StepException(Throwable cause) {
        super(cause);
    }
}
