package com.ps.controller.answer;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionVO;
import com.ps.service.QuestionService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author 26498
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Reference(version = "1.0.0")
    private QuestionService questionService;

    /**
     * 查询问题
     * @return
     */
    @GetMapping("/queryAll")
    public MessageVO queryAll(){

        List<QuestionVO> questionVOS = questionService.queryAll();

        return new MessageVO("0","成功",questionVOS);
    }


    /**
     * 根据关键字查询
     * @return
     */
    @GetMapping("/vagueQuery")
    public  MessageVO vagueQuery(@RequestParam String name) throws IOException, SolrServerException {

        MessageVO messageVO = questionService.vagueQuery(name);

        return messageVO;

    }


    /**
     * 发布问题
     * @param questionVO
     * @return
     */
    @GetMapping("/add")
    public MessageVO addQuestion(@RequestBody QuestionVO questionVO){

        //添加问题
        MessageVO message = questionService.addQuestion(questionVO);

        return message;
    }

    
    /**
     * 查询问题明细
     * @return
     */
    @GetMapping("/queryQuestionDetail")
     public MessageVO queryQuestionDetail(@RequestParam Integer questionId){

        List<QuestionVO> questionVOS = questionService.queryQuestionDetail(questionId);


        return new MessageVO("0","成功",questionVOS);
     }



}
