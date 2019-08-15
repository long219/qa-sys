package com.ps.mapper;

import com.ps.domain.IntegralVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 26498
 */
@Mapper
@Repository
public interface IntegralMapper {

    /**
     * 根据会员id查询积分流水
     * @param userId
     * @return
     */
    List<IntegralVO> queryAll(Integer userId);

    /**
     * 添加积分
     * @param integralVO
     */
    void  addIntegral(IntegralVO integralVO);


    /**
     * 根据积分流水id查询
     * @param id
     * @return
     */
    List<IntegralVO> queryById(Integer id);


}
