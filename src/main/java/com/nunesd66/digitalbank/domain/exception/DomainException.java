package com.nunesd66.digitalbank.domain.exception;

public abstract class DomainException extends RuntimeException {
    public DomainException(String msg) {
        super(msg);
    }
}
