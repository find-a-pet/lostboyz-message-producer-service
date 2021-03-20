package com.mtotowamkwe.lostboyzmessageproducerservice.api.impl;

import com.mtotowamkwe.lostboyzmessageproducerservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzmessageproducerservice.config.ProducerConfig;
import com.mtotowamkwe.lostboyzmessageproducerservice.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProducerConfig.class})
public class MessageProducerImplTest {

    @Mock
    private RabbitTemplate rabbitTemplateMock;

    @Mock
    private DirectExchange exchange;

    @Mock
    private MessageProducer sender;

    @InjectMocks
    private MessageProducerImpl senderImpl;

    @Test
    public void testTemplateIsNotNull() {
        assertNotNull(rabbitTemplateMock);
    }

    @Test
    public void testExchangeIsNotNull() {
        assertNotNull(exchange);
    }

    @Test
    public void testSenderIsNotNull() {
        assertNotNull(sender);
    }

    @Test
    public void testSenderImplIsNotNull() {
        assertNotNull(senderImpl);
    }

    @Test
    public void send() {
        assertThatCode(() -> this.senderImpl.send(Constants.TEST_MESSAGE_TO_BE_ENQUEUED)).doesNotThrowAnyException();
        Mockito.verify(this.rabbitTemplateMock)
                .convertAndSend(eq(this.exchange.getName()), eq("payload"), eq(Constants.TEST_MESSAGE_TO_BE_ENQUEUED));
    }
}