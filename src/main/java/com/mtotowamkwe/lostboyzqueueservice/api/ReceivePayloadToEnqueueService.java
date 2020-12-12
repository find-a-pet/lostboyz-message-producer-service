package com.mtotowamkwe.lostboyzqueueservice.api;

import com.mtotowamkwe.lostboyzqueueservice.exception.MessageNotEnqueuedException;
import org.springframework.http.ResponseEntity;

public interface ReceivePayloadToEnqueueService {

    ResponseEntity<?> getPayloadToBeEnqueued(String payload) throws MessageNotEnqueuedException;
}
