package com.mtotowamkwe.lostboyzqueueservice.api.impl;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageConsumer;
import com.mtotowamkwe.lostboyzqueueservice.exception.DequeuedMessageWasNotEmailedException;
import com.mtotowamkwe.lostboyzqueueservice.model.UserEmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.*;
import org.springframework.http.HttpStatus.Series;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class MessageConsumerImpl implements MessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumerImpl.class);

    private RestTemplate template = new RestTemplate();

    @RabbitListener(queues = "account.verification.emails")
    public void receive(String payload) throws
            RestClientException, DequeuedMessageWasNotEmailedException {
        String[] user = payload.split(",");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserEmailTemplate userEmailTemplate = new UserEmailTemplate();
        userEmailTemplate.setEmail(user[0]);
        userEmailTemplate.setCode(user[1]);

        try {
            ResponseEntity<UserEmailTemplate> responseEntity = template
                    .postForEntity(getUri(),
                            new HttpEntity<>(userEmailTemplate, headers),
                            UserEmailTemplate.class);

            HttpStatus status = responseEntity.getStatusCode();

            if (status.equals(Series.SUCCESSFUL)) {
                LOG.info("Verification code " + userEmailTemplate.getCode() + " sent to "
                        + userEmailTemplate.getEmail());
            } else if (status.equals(Series.CLIENT_ERROR) || status.equals(Series.SERVER_ERROR)){
                LOG.error("DequeuedMessageWasNotEmailedException @ receive()", responseEntity);
                throw new DequeuedMessageWasNotEmailedException("Email not sent:");
            }
        } catch (RestClientException rce) {
            LOG.error("RestClientException @ receive():", rce);
            throw rce;
        }

    }

    private URI getUri() throws DequeuedMessageWasNotEmailedException {
        try {
            URI url = new URI("http://127.0.0.1:14000/api/v1/email");
            return url;
        } catch (URISyntaxException usex) {
            LOG.error("URISyntaxException @ getUri():", usex);
            throw new DequeuedMessageWasNotEmailedException("Email not sent:\n" + usex.getMessage());
        }
    }
}
