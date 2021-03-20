package com.mtotowamkwe.lostboyzmessageproducerservice.exception;

public class DequeuedMessageWasNotEmailedException extends Throwable {

    public DequeuedMessageWasNotEmailedException(String errorMessage) {
        super(errorMessage);
    }
}
