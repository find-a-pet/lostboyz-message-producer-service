package com.mtotowamkwe.lostboyzqueueservice.api.impl;

import com.mtotowamkwe.lostboyzqueueservice.api.ReceivePayloadToEnqueueService;
import com.mtotowamkwe.lostboyzqueueservice.exception.MessageNotEnqueuedException;
import com.mtotowamkwe.lostboyzqueueservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@Service
@Controller
public class ReceivePayloadToEnqueueServiceImpl implements ReceivePayloadToEnqueueService {

    private static final Logger LOG = LoggerFactory.getLogger(ReceivePayloadToEnqueueServiceImpl.class);

    private MessageProducerImpl producer = new MessageProducerImpl();

    @Override
    @PostMapping(Constants.API_MESSAGE_PRODUCER_ENDPOINT)
    public ResponseEntity<?> getPayloadToBeEnqueued(@Valid @NonNull @RequestBody String payload) throws MessageNotEnqueuedException {
        try {
            producer.send(payload);
        } catch (MessageNotEnqueuedException mnsexc) {
            LOG.error("A MessageNotEnqueuedException occurred in the method getPayloadToBeEnqueued():\n", mnsexc);
            throw new MessageNotEnqueuedException(payload, mnsexc.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
