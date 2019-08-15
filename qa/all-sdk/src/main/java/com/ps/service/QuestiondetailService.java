package com.ps.service;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestiondetailVO;

import java.util.List;

public interface QuestiondetailService {


    /**
     * 查询所有问卷内容
     * @return
     */
    List<QuestiondetailVO> queryAll();


    /**
     * 添加问卷内容
     * @param questiondetailVO
     * @return
     */
    MessageVO add(QuestiondetailVO questiondetailVO);


    /**
     * 根据问卷id 查询问卷内容
     * @param questionnaireId
     * @return
     */
    List<QuestiondetailVO> queryByQuestionnaireId(Integer questionnaireId);

}
