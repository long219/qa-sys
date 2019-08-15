package com.ps.service.impl;

import com.ps.domain.CommodityVO;
import com.ps.mapper.CommodityExchangeMapper;
import com.ps.service.CommodityService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 26498
 */
@Service(version = "1.0.0")
public class CommodityServiceImpl implements CommodityService {


    @Autowired
    private CommodityExchangeMapper commodityExchangeMapper;

    @Override
    public List<CommodityVO> selectCommodityById(Integer id) {

        return commodityExchangeMapper.selectCommodityById(id);
    }

    
    @Override
    public int inventoryReduction(Integer id) {

        int i = commodityExchangeMapper.inventoryReduction(id);

        return i;
    }

}
