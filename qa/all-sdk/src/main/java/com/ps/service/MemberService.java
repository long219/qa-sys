package com.ps.service;

import com.ps.domain.MemberVO;
import com.ps.domain.MessageVO;

import java.util.List;

/**
 * @author 26498
 */
public interface MemberService {

   /**
    * 会员增加
    * @param memberVO
    * @return
    */
   MessageVO add(MemberVO memberVO);

   /**
    * 查询所有会员
    * @return
    */
   List<MemberVO> queryAll();

   /**
    * 根据用户id 查询用户信息
    * @return
    */
   List<MemberVO> queryById(Integer id);

   /**
    * 扣除，添加积分
    * @param id
    * @param point_number
    * @return
    */
   MessageVO deductPoint(Integer id,Integer point_number);
}
