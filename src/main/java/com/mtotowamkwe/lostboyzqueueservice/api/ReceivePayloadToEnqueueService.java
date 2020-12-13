package com.mtotowamkwe.lostboyzqueueservice.api;

import com.mtotowamkwe.lostboyzqueueservice.exception.MessageNotEnqueuedException;
import com.mtotowamkwe.lostboyzqueueservice.model.ReceiverPayload;
import org.springframework.http.ResponseEntity;

public interface ReceivePayloadToEnqueueService {

    ResponseEntity<?> getPayloadToBeEnqueued(ReceiverPayload payload) throws MessageNotEnqueuedException;
}
