package com.mtotowamkwe.lostboyzqueueservice.config;

import com.mtotowamkwe.lostboyzqueueservice.api.MessageConsumer;
import com.mtotowamkwe.lostboyzqueueservice.api.impl.MessageConsumerImpl;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    @Bean
    public Queue queue() {
        return new Queue("account.verification.emails");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("account.verification");
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("payload");
    }

    @Bean
    public MessageConsumer receiver() {
        return new MessageConsumerImpl();
    }
}
