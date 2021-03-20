package com.mtotowamkwe.lostboyzmessageproducerservice.config;

import com.mtotowamkwe.lostboyzmessageproducerservice.api.MessageProducer;
import com.mtotowamkwe.lostboyzmessageproducerservice.api.impl.MessageProducerImpl;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.mtotowamkwe.lostboyzmessageproducerservice"})
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
