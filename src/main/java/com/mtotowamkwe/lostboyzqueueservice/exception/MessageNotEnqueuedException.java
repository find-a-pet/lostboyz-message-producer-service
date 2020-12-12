package com.mtotowamkwe.lostboyzqueueservice.exception;

public class MessageNotEnqueuedException extends Throwable {

    public MessageNotEnqueuedException(String messageSupposedToBeEnqueued, String errorMessage) {
        super(messageSupposedToBeEnqueued +
                " was not sent because of the following error\n" + errorMessage);
    }
}
