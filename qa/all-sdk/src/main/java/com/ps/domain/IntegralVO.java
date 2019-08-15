package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 积分实体
 *
 * @author 26498
 */
@Data
public class IntegralVO implements Serializable {


    private Integer id;

    /**
     * 积分数量
     */
    private Integer detail;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 0：扣除，1：增加
     */
    private String type ;

    public IntegralVO(Integer detail, Integer user_id, String type) {
        this.detail = detail;
        this.user_id = user_id;
        this.type = type;
    }

    public IntegralVO() {
    }


}
