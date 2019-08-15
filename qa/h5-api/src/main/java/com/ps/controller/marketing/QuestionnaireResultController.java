package com.ps.controller.marketing;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionnaireResultVO;
import com.ps.service.MemberService;
import com.ps.service.QuestionnaireResultService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 26498
 */
@RestController
@RequestMapping("/questionnaireResult")
public class QuestionnaireResultController {

    /**
     * 问卷调查信息
     */
    @Reference(version = "1.0.0")
    private QuestionnaireResultService questionnaireResultService;


    /**
     * 会员service
     */
    @Reference(version = "1.0.0")
    private MemberService memberService;


    /**
     * 添加问卷调查信息
     * @param userId
     * @return
     */
    @GetMapping("/queryAll")
    public MessageVO queryAll(@RequestParam Integer userId){

        List<QuestionnaireResultVO> questionnaireResultVOS = questionnaireResultService.queryAll(userId);

        return  new MessageVO("0","成功",questionnaireResultVOS);

    }



    /**
     * 添加问卷调查
     *
     * @param userId
     * @param questionnareId
     * @param resultMap
     */
    @PostMapping("/add")
    public MessageVO add(@RequestParam("userId") Integer userId, @RequestParam("questionnareId") Integer questionnareId, @RequestBody Map<Integer,String> resultMap){

        MessageVO messageVO = new MessageVO();

        System.out.println("userId--"+userId+"  questionnareId--"+questionnareId);

        //提醒信息
        MessageVO add=null;

        String state="成功";

        //迭代问卷调查的信息，
        for (Integer integer : resultMap.keySet()) {

            QuestionnaireResultVO questionnaireResultVO = new QuestionnaireResultVO();

            //会员id
            questionnaireResultVO.setUser_id(userId);

            //问卷标题id
            questionnaireResultVO.setQuestionnare_id(questionnareId);

            //问卷内容id
            questionnaireResultVO.setQuestiondetail_id(integer);

            //选择(0：否，1：是)
            questionnaireResultVO.setChoose(resultMap.get(integer));

            //添加
            add= questionnaireResultService.add(questionnaireResultVO);


        }

       if (state.equals(add.getMsg())){

           //填写调查表后，送 10 积分
           memberService.deductPoint(userId,10);
       }

       return messageVO;

    }
}
