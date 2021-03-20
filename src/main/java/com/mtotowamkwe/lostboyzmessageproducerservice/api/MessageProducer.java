package com.mtotowamkwe.lostboyzmessageproducerservice.api;

import com.mtotowamkwe.lostboyzmessageproducerservice.exception.MessageNotEnqueuedException;
import org.springframework.http.ResponseEntity;

public interface MessageProducer {

    ResponseEntity<Boolean> send(String message) throws MessageNotEnqueuedException;
}
