package com.infosys.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String topic = "my_topic";

    public void sendMessage(String message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Sent: " + message);
    }
}