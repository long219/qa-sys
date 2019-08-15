package com.ps.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 26498
 *
 * 商品vo
 */
@Data
public class CommodityVO implements Serializable {

    /**
     * 商品id
     */
    private  Integer id;

    /**
     * 商品名称
     */
    private String name;


    /**
     * 商品数量
     */
    private Integer amount;

    /**
     * 兑换积分
     */
    private Integer integral;


    /**
     * 商品价格
     */
    private Integer money;


    /**
     * 开始时间
     */
    private Date startTime;


    /**
     * 结束时间
     */
    private Date entTime;

    /**
     * 版本号
     */
    private Integer version;

}
