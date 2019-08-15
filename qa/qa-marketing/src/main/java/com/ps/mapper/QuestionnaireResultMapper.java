package com.ps.mapper;

import com.ps.domain.QuestionnaireResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 26498
 */
@Mapper
@Repository
public interface QuestionnaireResultMapper {

    /**
     * 根据用户id 查询问卷调查信息
     * @param userId
     * @return
     */
    List<QuestionnaireResultVO> queryAll(Integer userId);


    /**
     * 添加问卷调查信息
     * @param questionnaireResultVO
     */
    void add(QuestionnaireResultVO questionnaireResultVO);


}
