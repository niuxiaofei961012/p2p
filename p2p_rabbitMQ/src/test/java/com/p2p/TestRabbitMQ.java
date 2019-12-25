package com.p2p;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRabbitMQ {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessage(){
        amqpTemplate.convertAndSend("myQueue","now:"+new Date());
    }
}
