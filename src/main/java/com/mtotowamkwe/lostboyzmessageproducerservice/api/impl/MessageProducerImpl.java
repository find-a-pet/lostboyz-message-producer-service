package com.mtotowamkwe.lostboyzmessageproducerservice.api.impl;

import com.mtotowamkwe.lostboyzmessageproducerservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzmessageproducerservice.exception.MessageNotEnqueuedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageProducerImpl implements MessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProducerImpl.class);

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    @Override
    public ResponseEntity<Boolean> send(String message) throws MessageNotEnqueuedException {
        try {
            template.convertAndSend(exchange.getName(), "payload", message);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AmqpException ae) {
            LOG.error("AmqpException @ send():", ae);
            throw new MessageNotEnqueuedException(message, ae.getMessage());
        }
    }
}
