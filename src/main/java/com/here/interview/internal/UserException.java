package com.here.interview.internal;

public class UserException extends Exception {

    public UserException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "UserException{" +
                "msg='" + getMessage() + '\'' +
                '}';
    }
}
