package com.mtotowamkwe.lostboyzqueueservice.config;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzqueueservice.api.impl.MessageProducerImpl;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("account.verification");
    }

    @Bean
    public MessageProducer sender() {
        return new MessageProducerImpl();
    }
}
