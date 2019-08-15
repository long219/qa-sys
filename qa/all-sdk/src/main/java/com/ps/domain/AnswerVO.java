package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 26498
 */
@Data
public class AnswerVO implements Serializable {


    private Integer id;

    /**
     * 回答内容
     */
    private String content ;


    /**
     * 问题id
     */
    private Integer question_id;

    /**
     * 用户id
     */
    private Integer user_id;


    /**
     * 0.未采纳   1.已采纳
     */
    private String type;
}
