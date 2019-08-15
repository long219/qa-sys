package com.ps.mapper;

import com.ps.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 26498
 */
@Mapper
public interface OrderMapper {

    /**
     * 添加订单
     * @param
     */
    void insertOder(Integer cId , Integer userId);


    /**
     * 根据订单id查询订单信息
     * @param id
     * @return
     */
    List<OrderVO> selectOrderById(Integer id);

    /**
     * 根据商品id 和 用户id 查询
     * @param cId
     * @param userId
     * @return
     */
    OrderVO selectOrderByCidAndUid(Integer cId,Integer userId);

}
