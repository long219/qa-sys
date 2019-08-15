package com.ps.domain;


import lombok.Data;

import java.io.Serializable;

/**
 * 会员实体
 *
 * @author 26498
 */
@Data
public class MemberVO implements Serializable {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户积分
     */
    private Integer point_number;


}
