package com.kafka.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerListener {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = "start-events", groupId = "my-group")
    public void listener(String message){
        System.out.println("Message received. Message: " + message);
    }
}
