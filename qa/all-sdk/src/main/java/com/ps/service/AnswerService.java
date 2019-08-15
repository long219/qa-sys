package com.ps.service;

import com.ps.domain.AnswerVO;
import com.ps.domain.MessageVO;

import java.util.List;

/**
 * @author 26498
 */
public interface AnswerService {


    /**
     * 查询回答信息
     * @return
     */
    List<AnswerVO> queryAll();


    /**
     * 添加回答
     * @param answerVO
     * @return
     */
    MessageVO addAnswer(AnswerVO answerVO);

    /**
     * 采纳问题
     * @param answerVO  回答的实例
     * @return
     */
    MessageVO updateAnswer(AnswerVO answerVO);

}
