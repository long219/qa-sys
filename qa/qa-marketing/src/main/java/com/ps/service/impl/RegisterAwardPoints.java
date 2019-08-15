package com.ps.service.impl;

import com.ps.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author 26498
 */
@Service
public class RegisterAwardPoints {

    /**
     * 设置赠送的积分
     */
    private final Integer POINT_NUMBER=100;

    public static Logger logger = LoggerFactory.getLogger(RegisterAwardPoints.class);

    /**
     * 会员service
     */
    @Reference(version = "1.0.0")
    private MemberService memberService;


    @KafkaListener(topics = "member")
    public void awardPoints(ConsumerRecord<?, ?> cr){

        //打印日志
        logger.info("{} ‐ {} : {}", cr.topic(), cr.key(), cr.value());

        Integer id= Integer.valueOf(cr.value().toString());

        //增加积分
        memberService.deductPoint(id,POINT_NUMBER);

    }



}
