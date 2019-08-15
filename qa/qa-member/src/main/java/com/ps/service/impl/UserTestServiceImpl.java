package com.ps.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserTestServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(UserTestServiceImpl.class);

    @KafkaListener(topics = "panshi")
    public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("nihao");
        logger.info("{} ‐ {} : {}", cr.topic(), cr.key(), cr.value());

    }
}
