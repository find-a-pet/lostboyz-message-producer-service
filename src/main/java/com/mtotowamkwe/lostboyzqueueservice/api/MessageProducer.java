package com.mtotowamkwe.lostboyzqueueservice.api;

import com.mtotowamkwe.lostboyzqueueservice.exception.MessageNotEnqueuedException;
import org.springframework.http.ResponseEntity;

public interface MessageProducer {

    ResponseEntity<Boolean> send(String message) throws MessageNotEnqueuedException;
}
