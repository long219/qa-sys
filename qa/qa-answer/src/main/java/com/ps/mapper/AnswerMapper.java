package com.ps.mapper;

import com.ps.domain.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 26498
 */
@Mapper
@Repository
public interface AnswerMapper {


    /**
     * 查询回答信息
     * @return
     */
    List<AnswerVO> queryAll();

    /**
     * 添加回答
     * @param answerVO
     */
    void addAnswer(AnswerVO answerVO);


    /**
     * 采纳问题
     * @param answerVO
     */
    void updateAnswer(AnswerVO answerVO);

}
