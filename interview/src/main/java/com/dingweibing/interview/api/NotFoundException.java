package com.dingweibing.interview.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-31T08:19:42.715Z")

public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
