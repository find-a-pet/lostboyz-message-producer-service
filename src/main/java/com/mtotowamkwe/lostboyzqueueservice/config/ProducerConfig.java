package com.mtotowamkwe.lostboyzqueueservice.config;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzqueueservice.api.impl.MessageProducerImpl;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Bean
    public MessageProducer sender() {
        return new MessageProducerImpl();
    }
}
