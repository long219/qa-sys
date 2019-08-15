package com.ps.controller.answer;

import cn.gjing.lock.AbstractLock;
import com.ps.domain.AnswerVO;
import com.ps.domain.MessageVO;
import com.ps.domain.QuestionVO;
import com.ps.service.AnswerService;
import com.ps.service.MemberService;
import com.ps.service.QuestionService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author 26498
 */

@RestController
@RequestMapping("/answer")
public class AnswerController {

    /**
     * 回答service
     */
    @Reference(version = "1.0.0")
    private AnswerService answerService;



    /**
     * 问题service
     */
    @Reference(version = "1.0.0")
    private QuestionService questionService;


    /**
     * 分布式锁的钥匙
     */
    private final String LOCK_KEY="answerLock";

    /**
     * 查询回答信息
     * @return
     */
    @GetMapping("/queryAll")
    public MessageVO queryAll(){

        List<AnswerVO> answerVOS = answerService.queryAll();

        return new MessageVO("0","成功",answerVOS);
    }


    /**
     * 添加回答信息
     * @param answerVO
     * @return
     */
    @PostMapping("/addAnswer")
    public MessageVO addAnswer(@RequestBody AnswerVO answerVO){

        MessageVO message =answerService.addAnswer(answerVO);

        return message;
    }


    /**
     * 采纳问题
     * @param answerVO
     * @return
     */
    @PostMapping("/updateAnswer")
    public MessageVO updateAnswer(@RequestBody AnswerVO answerVO){

        //采纳
        MessageVO message = answerService.updateAnswer(answerVO);

        return message;
    }




      /* private String answerLock(AnswerVO answerVO, String msg) {

        String lock = null;

        String message=null;

        try {

            //设置锁
            lock = this.abstractLock.lock(LOCK_KEY,getRandomString(5), 20, 10000, 50);

            if (state.equals(msg)){

                //获取问题信息
                QuestionVO questionVO = questionService.queryById(answerVO.getQuestion_id());

                //扣除提问者积分
                String deductState = memberService.deductPoint(questionVO.getUser_id(), -(questionVO.getReward()));

                //赠送被采纳者积分
                String addState = memberService.deductPoint(answerVO.getUser_id(), questionVO.getReward());

                if (state.equals(deductState) && state.equals(addState)){
                    message="成功";
                }else {
                    message="失败";
                }
            }

        }finally {

            this.abstractLock.release(LOCK_KEY,lock);
        }

        return message;

    }*/

}
