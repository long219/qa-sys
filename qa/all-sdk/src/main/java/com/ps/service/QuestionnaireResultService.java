package com.ps.service;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionnaireResultVO;

import java.util.List;

/**
 * @author 26498
 */
public interface QuestionnaireResultService {


    /**
     * 根据用户id 查询问卷调查信息
     * @param userId
     * @return
     */
    List<QuestionnaireResultVO> queryAll(Integer userId);


    /**
     * 添加问卷调查信息
     * @param questionnaireResultVO
     * @return
     */
    MessageVO add(QuestionnaireResultVO questionnaireResultVO);

}
