package com.mtotowamkwe.lostboyzqueueservice.api.impl;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzqueueservice.exception.MessageNotEnqueuedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageProducerImpl implements MessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProducerImpl.class);

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    @Override
    public boolean send(String message) throws MessageNotEnqueuedException {
        try {
            template.convertAndSend(exchange.getName(), "payload", message);
            return true;
        } catch (AmqpException ae) {
            LOG.error("AmqpException occurred in the method send():\n", ae);
            throw new MessageNotEnqueuedException(message, ae.getMessage());
        }
    }
}
