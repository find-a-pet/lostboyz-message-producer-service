package com.mtotowamkwe.lostboyzmessageproducerservice.exception;

public class BadRestTemplateUrlException extends Throwable {

    public BadRestTemplateUrlException(String errorMessage) {
        super("The lostboyz-email-service was not called because of the following error:\n" + errorMessage);
    }
}
