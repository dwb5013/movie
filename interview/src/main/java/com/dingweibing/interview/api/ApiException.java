package com.dingweibing.interview.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-31T08:19:42.715Z")

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
