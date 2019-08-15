package com.ps.mapper;

import com.ps.domain.CommodityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 26498
 */
@Mapper
public interface CommodityExchangeMapper {

    /**
     * 根据商品id查询商品
     * @param id
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
