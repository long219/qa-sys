package com.ps.controller.marketing;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestiondetailVO;
import com.ps.service.QuestiondetailService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 26498
 */
@RestController
@RequestMapping("/questiondetail")
public class QuestiondetailController {

    @Reference(version = "1.0.0")
    private QuestiondetailService questiondetailService;


    /**
     * 根据问卷标题id 查询问卷内容
     * @return
     */
    @GetMapping("/queryByQuestionnaireId")
    public MessageVO queryAll(@RequestParam Integer questionnaireId){

        List<QuestiondetailVO> questiondetailVOS = questiondetailService.queryByQuestionnaireId(questionnaireId);

        return  new MessageVO("0","成功",questiondetailVOS);
    }


    /**
     * 添加问卷内容
     * @param questiondetailVO
     * @return
     */
    @PostMapping("/add")
    public MessageVO queryAll(@RequestBody QuestiondetailVO questiondetailVO){

        MessageVO messageVO = questiondetailService.add(questiondetailVO);

        return messageVO;

    }





}
