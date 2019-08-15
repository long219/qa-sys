package com.ps.controller.member;

import com.ps.domain.MemberVO;
import com.ps.domain.MessageVO;
import com.ps.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


/**
 * @author 26498
 */
@RestController
@RequestMapping("/user")
public class MemberController {

    @Reference(version = "1.0.0")
    private MemberService memberService;


    /**
     * 存在邀请用户注册的积分
     */
    private final Integer POINT_NUMBER=100;

    /**
     * 注册
     * @param memberVO
     * @return
     */
    @PostMapping("/add")
    public MessageVO add(@RequestBody MemberVO memberVO){

        //判断是否有用户id， 如果有就根据用户id添加积分
        if (memberVO.getId()!=null){

            memberService.deductPoint(memberVO.getId(),POINT_NUMBER);
        }

        MessageVO  messageVO= memberService.add(memberVO);

        return messageVO;

    }


    /**
     * 要请会员注册
     * @return
     */
    @GetMapping("/invite")
    public MessageVO inviteAdd(@RequestParam Integer userId){

        return new MessageVO("0","成功","http://localhost:8099/user/add?token="+userId);
    }
}
