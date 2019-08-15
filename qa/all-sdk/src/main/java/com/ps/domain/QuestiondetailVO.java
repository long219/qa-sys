package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 文卷内容
 *
 * @author 26498
 */
@Data
public class QuestiondetailVO implements Serializable {

    /**
     * 文卷内容id
     */
    private Integer id;

    /**
     * 文卷内容
     */
    private String name;

    /**
     * 文卷标题id
     */
    private Integer questionnare_id;
}
