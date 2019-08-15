package com.ps.service;

import com.ps.domain.IntegralVO;

import java.util.List;

/**
 * @author 26498
 */
public interface IntegralService {

    /**
     * 根据会员id查询积分流水
     * @param userId
     * @return
     */
    List<IntegralVO> queryByUserId(Integer userId);

    /**
     * 添加积分
     * @param integralVO
     * @return
     */
    String addIntegral(IntegralVO integralVO);


    /**
     * 根据积分流水id查询
     * @param id
     * @return
     */
    List<IntegralVO> queryById(Integer id);



}
