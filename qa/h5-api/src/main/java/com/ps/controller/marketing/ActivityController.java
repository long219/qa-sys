package com.ps.controller.marketing;

import com.ps.domain.MessageVO;

import com.ps.service.ActivityService;
import javafx.beans.binding.StringBinding;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.concurrent.TimeUnit;


/**
 * @author 26498
 *
 * 活动controller
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Reference(version = "1.0.0")
    private ActivityService activityService;


    /**
     * 注入redis
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/exchange")
    public MessageVO  exchange(@RequestParam Integer cId , Integer userId) throws InterruptedException {

        String  state = stringRedisTemplate.opsForValue().get("active:limit:" + userId);

        if (state==null){

            //添加消息队列
            boolean addQueue = addQueue(userId,cId);

            if (addQueue){
                //排队成功
                stringRedisTemplate.opsForValue().set("active:limit:" + userId,"2",1,TimeUnit.DAYS);
                state="2";

            }else {

                //排队失败
                stringRedisTemplate.opsForValue().set("active:limit:" + userId,"1",1,TimeUnit.DAYS);
                state="1";

            }

        }

        MessageVO messageVO= new MessageVO();

        if("2".equals(state)){
            messageVO.setCode("0");
            messageVO.setMsg("排队成功");
        }

        if ("1".equals(state)){
            messageVO.setCode("0");
            messageVO.setMsg("排队人数较多！！");
        }

        return  messageVO;

    }


    /**
     * 获取秒杀队列的长度
     */
    @Value("${seckillGoods.queueSize}")
    Integer queueSize;


    /**
     * 消息队列
     * @return
     */
    public boolean addQueue(Integer userId,Integer cId){

        //获取队列长度
        Long size = stringRedisTemplate.opsForList().size("active:exchangeQueue");


        //判断队列的最大容量
        if (size<=queueSize){

            //添加队列
            stringRedisTemplate.opsForList().leftPush("active:exchangeQueue",userId+","+cId);

            return  true;
        }

        return false;

    }



    /**
     * 获取随机数
     * @return
     */
    public long getRandm(){

        long round = Math.round(Math.random() * 100000);

        return round;

    }





    @PostMapping("/exchange2")
    public MessageVO  exchange2(@RequestParam Integer cId , Integer userId) throws InterruptedException {


        Boolean aBoolean = stringRedisTemplate.hasKey("user:" + userId);

        //判断key 是否存在 如果存在就返回虚的信息
        if (aBoolean==true){
            return  new MessageVO("0","同一个人");
        }


        //将信息存入redis
        stringRedisTemplate.opsForValue().set("userId:limit:"+userId,cId+"",5, TimeUnit.SECONDS);


        MessageVO exchange = activityService.exchange(cId, userId);




        return  exchange;

    }
}
