package com.ps.mapper;

import com.ps.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 26498
 */
@Mapper
@Repository
public interface MemberMapper {

    /**
     * 会员增加
     * @param memberVO
     */
    void  add(MemberVO memberVO);

    /**
     * 查询所有会员信息
     * @return
     */
    List<MemberVO> queryAll();

    /**
     * 根据用户id 查询用户信息
     * @param id
     * @return
     */
    List<MemberVO> queryById(Integer id);

    /**
     * 扣除,增加积分
     * @param id
     * @param point_number
     */
    void  deductPoint(Integer id,Integer point_number);

}
