
package com.wanxi.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "LeaderQueue")
public class LeaderListener {

    @RabbitHandler
    public void receive(String message){
        System.out.println("领导队列监听到消息: " + message);
    }
}
