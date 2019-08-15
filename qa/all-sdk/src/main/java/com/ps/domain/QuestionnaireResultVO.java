package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 26498
 */
@Data
public class QuestionnaireResultVO implements Serializable {


    public QuestionnaireResultVO( Integer questionnare_id, Integer user_id, Integer questiondetail_id, String choose) {
        this.questionnare_id = questionnare_id;
        this.user_id = user_id;
        this.questiondetail_id = questiondetail_id;
        this.choose = choose;
    }

    public QuestionnaireResultVO() {
    }

    Integer id;

    /**
     * 问卷标题ID
     */
    Integer questionnare_id;

    /**
     * 用户id
     */
    Integer user_id;

    /**
     * 问卷内容id
     */
    Integer questiondetail_id;


    /**
     * 0：否，1：是
     */
    String choose ;
}
