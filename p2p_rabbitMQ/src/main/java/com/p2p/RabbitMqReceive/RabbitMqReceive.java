package com.p2p.RabbitMqReceive;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RabbitMqReceive {
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void ReceiveMessage(byte[] message){
        log.info("message================="+ SerializationUtils.deserialize(message));
    }
    }
