package com.mtotowamkwe.lostboyzqueueservice.api.impl;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageConsumer;
import com.mtotowamkwe.lostboyzqueueservice.exception.DequeuedMessageWasNotEmailedException;
import com.mtotowamkwe.lostboyzqueueservice.model.UserEmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.*;
import org.springframework.http.HttpStatus.Series;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class MessageConsumerImpl implements MessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumerImpl.class);

    private RestTemplate template = new RestTemplate();

    @RabbitListener(queues = "account.verification.emails")
    public void receive(String payload) throws DequeuedMessageWasNotEmailedException {
        String[] user = payload.split(",");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserEmailTemplate userEmailTemplate = new UserEmailTemplate();
        userEmailTemplate.setEmailAddress(user[0]);
        userEmailTemplate.setCode(user[1]);

        ResponseEntity<UserEmailTemplate> responseEntity = template
                .postForEntity(getUri(),
                        new HttpEntity<>(userEmailTemplate, headers),
                        UserEmailTemplate.class);

        HttpStatus status = responseEntity.getStatusCode();

        if (status.equals(Series.SUCCESSFUL)) {
            LOG.info("The code " + userEmailTemplate.getCode() +
                    " was successfully emailed to " + userEmailTemplate.getEmailAddress());
        } else if (status.equals(Series.CLIENT_ERROR) || status.equals(Series.SERVER_ERROR)){
            LOG.error("A DequeuedMessageWasNotEmailedException occurred in the method receive()",
                    responseEntity);
            throw new DequeuedMessageWasNotEmailedException(
                    "The message that was received from the queue was not sent to the lostboyz-email-service.");
        }
    }

    private URI getUri() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(14000)
                .path("/api/v1/email")
                .build()
                .toUri();
    }
}
