package com.bilgeadam.config.rabbitmq;

import static com.bilgeadam.constant.RabbitKeys.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {
    private String exchangeName = EXCHANGE_NAME;

    private String routingKeyCreateUser = ROUTING_KEY_CREATE_USER;
    private String queNameCreateUser = QUE_CREATE_USER;

    @Bean
    Queue queue(){
        return new Queue(queNameCreateUser);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingKeyCreateUser);
    }
}
