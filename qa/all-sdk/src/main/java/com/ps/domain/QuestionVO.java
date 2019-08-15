package com.ps.domain;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.List;


/**
 *
 * 问题实体
 *
 * @author 26498
 *
 */

@Data
public class QuestionVO implements Serializable {

    @Field("mId")
    private Integer id ;

    /**
     * 问题内容
     */

    @Field(value = "questionName")
    private List<String> questionName;

    private String content;

    /**
     * 悬赏积分
     */
    @Field(value = "reward")
    private Integer reward;

    /**
     * 用户id
     */
    @Field(value = "user_id")
    private Integer user_id;


    /**
     * 回答的实例 list
     */
    @Field(value = "answerName")
    private List<AnswerVO> answerVOList;


}
