package com.ps.service;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionVO;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * @author 26498
 */
public interface QuestionService {

    /**
     * 查询所有问题
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
     * 查询问题明细
     * @param questionId
     * @return
     */
    List<QuestionVO> queryQuestionDetail(Integer questionId);

    /**
     * 添加问题
     * @param questionVO
     * @return
     */
    MessageVO addQuestion(QuestionVO questionVO);

    /**
     * 根据关键字查询
     * @param name
     * @return
     */
    MessageVO vagueQuery(String name) throws SolrServerException, IOException;

}
