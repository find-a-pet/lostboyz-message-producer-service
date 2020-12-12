package com.mtotowamkwe.lostboyzqueueservice.exception;

public class DequeuedMessageWasNotEmailedException extends Throwable {

    public DequeuedMessageWasNotEmailedException(String errorMessage) {
        super(errorMessage);
    }
}
