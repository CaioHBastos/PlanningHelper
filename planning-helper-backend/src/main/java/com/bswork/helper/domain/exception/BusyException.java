package com.bswork.helper.domain.exception;

public class BusyException extends RuntimeException {

    public BusyException(String mensage) {
        super(mensage);
    }
}
