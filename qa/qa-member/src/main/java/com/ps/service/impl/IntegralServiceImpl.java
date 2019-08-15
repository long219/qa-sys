package com.ps.service.impl;


import com.ps.domain.IntegralVO;
import com.ps.mapper.IntegralMapper;
import com.ps.service.IntegralService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author 26498
 */
@Service(version = "1.0.0")
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public List<IntegralVO> queryByUserId(Integer userId) {

        return integralMapper.queryAll(userId);

    }

    @Override
    public String addIntegral(IntegralVO integralVO) {

        integralMapper.addIntegral(integralVO);

        return "添加成功";
    }

    @Override
    public List<IntegralVO> queryById(Integer id) {

        return integralMapper.queryById(id);
    }
}
