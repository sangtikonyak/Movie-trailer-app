package com.example.capstone.Notification.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
   private String exchangeName="movie-exchange";
   private String registerQueue="movie.queue";

    @Bean
    public DirectExchange setExchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue setQueue(){
        return new Queue(registerQueue);
    }
    @Bean
    public Jackson2JsonMessageConverter produceJackson2JsonMessage(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    Binding setBinding(DirectExchange exchange){
        return BindingBuilder.bind(setQueue()).to(exchange).with("movie-routing");
    }
}
