package com.bilgeadam.config.rabbitmq;

import static com.bilgeadam.constants.RabbitKeys.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {


    /**
     * Exchange name verelim
     */
    private String excahangeName= EXCHANGE_NAME;
    /**
     * RoutingKey ve QueueName verelim bunlar eşleşecekler
     */
    private String routingKeyCreateUser = ROUTING_KEY_CREATE_USER;
    private String queueNameCreateUser = QUE_CREATE_USER;

    private String routingKeyDeleteUser = ROUTING_KEY_DELETE_USER;
    private String queueNameDeleteUser = QUE_DELETE_USER;

    private String routingProfileKey = ROUTING_KEY_PROFILE;
    private String queueProfileSave = QUE_SAVE_PROFILE;

    @Bean
    Queue queueProfileSave(){
        return new Queue(queueProfileSave);
    }


    @Bean
    Queue queue(){
        return new Queue(queueNameCreateUser);
    }

    @Bean
    Queue queueDelete(){
        return new Queue(queueNameDeleteUser);
    }


    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(excahangeName);
    }

    @Bean
    public Binding binding(final Queue queue,final DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingKeyCreateUser);
    }

    @Bean
    public Binding bindingDelete(final Queue queueDelete,final DirectExchange directExchange){
        return BindingBuilder.bind(queueDelete).to(directExchange).with(routingKeyDeleteUser);
    }

    @Bean
    public Binding bindingProfile(final Queue queueProfileSave,final DirectExchange directExchange){
        return BindingBuilder.bind(queueProfileSave).to(directExchange).with(routingProfileKey);
    }
}
