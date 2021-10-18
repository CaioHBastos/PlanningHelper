package com.bswork.helper.dataprovider.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String mensage) {
        super(mensage);
    }
}
