package com.mtotowamkwe.lostboyzqueueservice.api;

import com.mtotowamkwe.lostboyzqueueservice.exception.MessageNotEnqueuedException;

public interface MessageProducer {

    boolean send(String message) throws MessageNotEnqueuedException;
}
