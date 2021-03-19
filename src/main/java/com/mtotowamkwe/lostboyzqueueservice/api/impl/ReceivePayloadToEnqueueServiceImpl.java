package com.mtotowamkwe.lostboyzqueueservice.api.impl;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzqueueservice.api.ReceivePayloadToEnqueueService;
import com.mtotowamkwe.lostboyzqueueservice.exception.MessageNotEnqueuedException;
import com.mtotowamkwe.lostboyzqueueservice.model.ReceiverPayload;
import com.mtotowamkwe.lostboyzqueueservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

    private final MessageProducer producer;

    public ReceivePayloadToEnqueueServiceImpl(MessageProducer producer) {
        this.producer = producer;
    }

    @Override
    @PostMapping(Constants.API_MESSAGE_PRODUCER_ENDPOINT)
    public ResponseEntity<?> getPayloadToBeEnqueued(@Valid @NonNull @RequestBody ReceiverPayload payload)
            throws MessageNotEnqueuedException {

        // Example payload:- {"payload":"alice@lostboyz.com,123456"}
        String emailAndVerificationCodePayload = payload.getPayload();

        try {
            return producer.send(emailAndVerificationCodePayload);
        } catch (MessageNotEnqueuedException mnsexc) {
            LOG.error("MessageNotEnqueuedException @ getPayloadToBeEnqueued():", mnsexc);
            throw new MessageNotEnqueuedException(emailAndVerificationCodePayload, mnsexc.getMessage());
        }
    }
}
