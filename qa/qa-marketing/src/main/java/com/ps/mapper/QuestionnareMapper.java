package com.ps.mapper;

import com.ps.domain.QuestionnareVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 26498
 */
@Mapper
@Repository
public interface QuestionnareMapper {

    /**
     * 查询所有问卷标题
     * @return
     */
    List<QuestionnareVO> queryAll();


    /**
     * 添加问卷标题
     * @param questionnareVO
     */
    void addQuestionnare(QuestionnareVO questionnareVO);

}
