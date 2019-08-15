package com.ps.service.impl;

import com.ps.domain.OrderVO;
import com.ps.mapper.OrderMapper;
import com.ps.service.OrderService;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 26498
 */
@Service(version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void insertOder(Integer cId , Integer userId) {

        orderMapper.insertOder(cId , userId);
    }

    @Override
    public List<OrderVO> selectOrderById(Integer id) {
        return orderMapper.selectOrderById(id);
    }

    @Override
    public OrderVO selectOrderByCidAndUid(Integer cId, Integer userId) {

        return orderMapper.selectOrderByCidAndUid(cId,userId);
    }
}
