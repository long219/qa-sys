package com.ps.service;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionnareVO;

import java.util.List;

public interface QuestionnareService {


    /**
     * 查询所有问卷标题
     * @return
     */
    List<QuestionnareVO> queryAll();


    /**
     * 添加问卷标题
     * @param questionnareVO
     * @return
     */
    MessageVO add(QuestionnareVO questionnareVO);

}
