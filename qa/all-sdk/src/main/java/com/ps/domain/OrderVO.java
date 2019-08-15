package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 26498
 */
@Data
public class OrderVO implements Serializable {

    private Integer id;
    private Integer userId;

    /**
     * 商品id
     */
    private Integer cId;
    private String orderTime;
}
