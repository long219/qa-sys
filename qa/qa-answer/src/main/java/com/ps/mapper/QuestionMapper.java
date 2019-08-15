package com.ps.mapper;

import com.ps.domain.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 26498
 */
@Mapper
@Repository
public interface QuestionMapper {

    /**
     * 查询所有问题信息
     * @return
     */
    List<QuestionVO> queryAll();


    /**
     * 根据问题id查询
     * @param id
     * @return
     */
    QuestionVO queryById(Integer id);


    /**
     * 查询明细
     * @param questionId
     * @return
     */
    List<QuestionVO> queryQuestionDetail(Integer questionId);


    /**
     * 添加问题
     * @param questionVO
     */
    void addQuestion(QuestionVO questionVO);



}
