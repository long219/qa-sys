package com.ps.service.impl;

import com.ps.domain.MessageVO;
import com.ps.service.MemberService;
import com.ps.service.QuestionService;
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
public class AnswerAwardPoints {

    public static Logger logger = LoggerFactory.getLogger(AnswerAwardPoints.class);


    /**
     * 会员service
     */
    @Reference(version = "1.0.0")
    private MemberService memberService;


    /**
     * 问题service
     */
    @Reference(version = "1.0.0")
    private QuestionService questionService;


    @KafkaListener(topics = "answer")
    public void awardPoints(ConsumerRecord<?, ?> cr){

        //打印日志
        logger.info("{} ‐ {} : {}", cr.topic(), cr.key(), cr.value());

        String str= (String) cr.value();

        String[] split = str.split(",");


        //扣除提问者积分
        MessageVO deductMessage = memberService.deductPoint(Integer.valueOf(split[0]), -Integer.valueOf(split[2]));


        //赠送双倍的积分
        int point = Integer.valueOf(split[2])*2;

        //赠送被采纳者积分
        MessageVO addMessage = memberService.deductPoint(Integer.valueOf(split[1]), point);

        //打印日志
        logger.info(String.valueOf(deductMessage.getCode().equals(addMessage.getCode())));


    }


}
