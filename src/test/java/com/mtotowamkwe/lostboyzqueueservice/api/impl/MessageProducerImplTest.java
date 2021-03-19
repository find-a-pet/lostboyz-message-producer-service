package com.mtotowamkwe.lostboyzqueueservice.api.impl;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzqueueservice.config.ConsumerConfig;
import com.mtotowamkwe.lostboyzqueueservice.config.ProducerConfig;
import com.mtotowamkwe.lostboyzqueueservice.config.Swagger;
import com.mtotowamkwe.lostboyzqueueservice.util.Constants;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.platform.commons.logging.LoggerFactory.getLogger;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProducerConfig.class, ConsumerConfig.class})
@SpringBootTest(classes = {MessageProducerImpl.class})
public class MessageProducerImplTest {

    @Mock
    private RabbitTemplate rabbitTemplateMock;

    @Autowired
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