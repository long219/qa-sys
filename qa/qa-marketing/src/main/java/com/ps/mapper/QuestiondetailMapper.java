package com.ps.mapper;

import com.ps.domain.QuestiondetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 26498
 */
@Mapper
@Repository
public interface QuestiondetailMapper {

    /**
     * 查询所有问卷内容
     * @return
     */
    List<QuestiondetailVO> queryAll();


    /**
     * 根据问卷id 查询问卷内容
     * @param questionnaireId
     * @return
     */
    List<QuestiondetailVO> queryByQuestionnaireId(Integer questionnaireId);


    /**
     * 添加问卷内容
     * @param questiondetailVO
     */
    void  add(QuestiondetailVO questiondetailVO);


}
