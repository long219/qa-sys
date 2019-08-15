package com.ps.controller.marketing;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionnareVO;
import com.ps.service.QuestionnareService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 26498
 */
@RestController
@RequestMapping("/questionnare")
public class QuestionnareController {

    @Reference(version = "1.0.0")
    private QuestionnareService questionnareService;


    /**
     * 添加标题
     * @param questionnareVO
     * @return
     */
    @PostMapping("/add")
    public MessageVO add(@RequestBody QuestionnareVO questionnareVO){

        MessageVO messageVO = questionnareService.add(questionnareVO);

        return messageVO;
    }

}
