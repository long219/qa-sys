package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 数据消息实体
 *
 * @author 26498
 *
 */
@Data
public class MessageVO <T> implements Serializable {

    public MessageVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public MessageVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MessageVO() {

    }

    /**
     * 编码 (0:成功  1:失败)
     */
    private String code ;

    /**
     * 消息体
     */
    private String msg;


    /**
     * 数据源
     */
    private T data;

}
