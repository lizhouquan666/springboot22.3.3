package com.wanxi.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue empQueue() {
        return new Queue("EmpQueue",true);
    }
    @Bean
    public Queue leaderQueue() {
        return new Queue("LeaderQueue",true);
    }

    @Bean
    DirectExchange userDirectExchange() {
        return new DirectExchange("userDirectExchange",true,false);
    }

    @Bean
    FanoutExchange userFanoutExchange() {
        return new FanoutExchange("userFanoutExchange",true,false);
    }

    @Bean
    TopicExchange userTopicExchange() {
        return new TopicExchange("userTopicExchange",true,false);
    }

    @Bean
    Binding empQueueToUserDirect() {
        return BindingBuilder.bind(empQueue()).to(userDirectExchange()).with("emp.queue");
    }
    @Bean
    Binding leaderQueueToUserDirect() {
        return BindingBuilder.bind(leaderQueue()).to(userDirectExchange()).with("leader.queue");
    }

    @Bean
    Binding empQueueToUserFanout() {
        return BindingBuilder.bind(empQueue()).to(userFanoutExchange());
    }
    @Bean
    Binding leaderQueueToUserFanout() {
        return BindingBuilder.bind(leaderQueue()).to(userFanoutExchange());
    }

    @Bean
    Binding empQueueToUserTopic() {
        return BindingBuilder.bind(empQueue()).to(userTopicExchange()).with("emp.#");
    }

    @Bean
    Binding leaderQueueToUserTopic() {
        return BindingBuilder.bind(leaderQueue()).to(userTopicExchange()).with("leader.#");
    }


}
