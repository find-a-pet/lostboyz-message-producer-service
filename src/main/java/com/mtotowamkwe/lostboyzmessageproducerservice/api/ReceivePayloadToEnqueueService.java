package com.mtotowamkwe.lostboyzmessageproducerservice.api;

import com.mtotowamkwe.lostboyzmessageproducerservice.exception.MessageNotEnqueuedException;
import com.mtotowamkwe.lostboyzmessageproducerservice.model.ReceiverPayload;
import org.springframework.http.ResponseEntity;

public interface ReceivePayloadToEnqueueService {

    ResponseEntity<?> getPayloadToBeEnqueued(ReceiverPayload payload) throws MessageNotEnqueuedException;
}
