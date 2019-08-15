package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 问卷标题实例
 * @author 26498
 */
@Data
public class QuestionnareVO implements Serializable {

    /**
     * 问卷标题Id
     */
   private Integer id;

    /**
     * 问卷标题
     */
   private String name;


}
