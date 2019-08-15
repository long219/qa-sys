package com.ps.service.impl;

import com.ps.domain.CommodityVO;
import com.ps.domain.MemberVO;
import com.ps.domain.MessageVO;
import com.ps.domain.OrderVO;
import com.ps.exception.BusinessException;
import com.ps.service.*;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author 26498
 */
@Service(version = "1.0.0")
public class ActivityServiceImpl implements ActivityService {


    /**
     * 商品 service
     */
    @Autowired
    private CommodityService commodityService;


    /**
     * 订单 service
     */
    @Autowired
    private OrderService orderService;

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


    /**
     * 注入消息队列
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 注入redis
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 注入 线程池
     */
    @Autowired
    private ExecutorService executorService;


    @Override
    @Transactional
    public MessageVO exchange(Integer cId , Integer userId) throws InterruptedException {

        // 1.根据用户id 查询用户信息
        MemberVO memberVO=memberService.queryById(userId).get(0);

        // 2.根据商品id查询商品信息
        CommodityVO commodityVO=commodityService.selectCommodityById(cId).get(0);
        

        // 3.校验用户是否存在
        if (memberVO==null){

            return new MessageVO("1","该用户不存在");

        }

        // 4.校验是否有库存
        if (commodityVO.getAmount()==0){
            return new MessageVO("1","没有库存了！");
        }


        //将活动开始日期转为毫秒数
        Date startTime = commodityVO.getStartTime();
        //将活动开始日期转为毫秒数
        Date entTime = commodityVO.getEntTime();

        //获取当前时间
        Date date = new Date();

        // 5.校验活动日期
        if (date.after(startTime) && date.before(entTime)){

            // 6. 校验积分是否足够
            if (memberVO.getPoint_number() < commodityVO.getAmount()){
                return  new MessageVO("1","您的积分余额不够！");
            }


            // 7.根据用户id 和商品id 查询订单信息
            OrderVO orderVO =orderService.selectOrderByCidAndUid(cId,userId);

            // 8.校验用户是否兑换过
            if(orderVO!=null){
                return  new MessageVO("1","您已兑换过了！");
            }


            // 9.扣积分
            MessageVO messageVO = memberService.deductPoint(userId, (-commodityVO.getIntegral()));

            Thread.sleep(10000);


            // 10.减库存
            int i = commodityService.inventoryReduction(cId);
            System.out.println("i-----------"+i);

            if (i==0){
                System.out.println("------流水表iD-----"+messageVO.getData());

                //积分退还
                kafkaTemplate.send("commodity","returnIntegral",messageVO.getData()+"");

                System.out.println("没有库存了----用户Id:"+userId);

                throw  new BusinessException( "0","没有库存了");
            }

            // 11.创建订单
            orderService.insertOder(cId , userId);


            return new MessageVO("0","秒杀成功");

        }else{

            return  new MessageVO("1","该活动已结束了！");
        }

    }


    @Scheduled(cron = "0/10 * * * * *")
    public void seckillGoods()  {


        for (int i = 0; i <10 ; i++) {

            executorService.submit(() -> {

                //1.读取开始消费
                String s = stringRedisTemplate.opsForList().rightPop("active:exchangeQueue", 10, TimeUnit.SECONDS);

                //获取用户id
                Integer userId = Integer.valueOf(s.split(",")[0]);
                //获取商品id
                Integer cId = Integer.valueOf(s.split(",")[1]);


                // 3.兑换商品
                try {

                    System.out.println("用户Id:"+userId+"---------"+"商品Id:"+cId);

                    exchange(cId,userId);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            });
        }




    }
}
