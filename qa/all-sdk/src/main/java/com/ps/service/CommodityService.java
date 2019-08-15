package com.ps.service;


import com.ps.domain.CommodityVO;


import java.util.List;

/**
 * @author 26498
 */

public interface CommodityService {


    /**
     * 根据商品id查询商品
     * @return
     */
    List<CommodityVO> selectCommodityById(Integer id);


    /**
     * 根据商品id减库存
     * @param id
     * @return
     */
    int inventoryReduction(Integer id);

}
