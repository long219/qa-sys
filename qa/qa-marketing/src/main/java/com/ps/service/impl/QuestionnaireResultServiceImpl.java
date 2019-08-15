package com.ps.service.impl;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionnaireResultVO;
import com.ps.mapper.QuestionnaireResultMapper;
import com.ps.service.QuestionnaireResultService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 26498
 */
@Service(version = "1.0.0")
public class QuestionnaireResultServiceImpl implements QuestionnaireResultService {


    /**
     * 记录用户id
     */
    private Integer userId;


    @Autowired
    private QuestionnaireResultMapper questionnaireResultMapper;

    @Override
    public List<QuestionnaireResultVO> queryAll(Integer userId) {
        return questionnaireResultMapper.queryAll(userId);
    }


    @Override
    public MessageVO add(QuestionnaireResultVO questionnaireResultVO) {

        userId=questionnaireResultVO.getUser_id();

        questionnaireResultMapper.add(questionnaireResultVO);

        return new MessageVO("0","成功");
    }


}
