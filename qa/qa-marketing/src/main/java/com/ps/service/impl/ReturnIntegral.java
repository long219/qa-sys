package com.ps.service.impl;

import com.ps.domain.IntegralVO;
import com.ps.service.IntegralService;
import com.ps.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


/**
 * @author 26498
 */
@Service
@Slf4j
public class ReturnIntegral {

    /**
     * 会员 service
     */
    @Reference(version = "1.0.0")
    private MemberService memberService;


    /**
     * 流水 service
     */
    @Reference(version = "1.0.0")
    private IntegralService integralService;


    @KafkaListener(topics = "commodity")
    public void  returnIntegral(ConsumerRecord<?, ?> cr){

        //打印日志
        log.info("{} ‐ {} : {}", cr.topic(), cr.key(), cr.value());


        int intergraId = Integer.parseInt((String) cr.value());

        //根据积分流水id获取积分流水信息
        IntegralVO integralVOS = integralService.queryById(intergraId).get(0);


        System.out.println(integralVOS.getUser_id()+"-------"+integralVOS.getDetail());


        int abs = Math.abs(integralVOS.getDetail());

        ////如果没有库存了 ，将扣除的积分退还回去
        memberService.deductPoint(integralVOS.getUser_id(),abs);

    }

}
