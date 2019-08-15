package com.ps.controller.member;

import com.ps.domain.IntegralVO;
import com.ps.domain.MessageVO;
import com.ps.service.IntegralService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 26498
 */
@RestController
@RequestMapping("/integral")
public class IntegralController {

    @Reference(version = "1.0.0")
    private IntegralService integralService;

    @GetMapping("/queryAll")
    public MessageVO queryAll(@RequestParam Integer userId){

        List<IntegralVO> integralVOS = integralService.queryByUserId(userId);

        return new MessageVO("0","成功",integralVOS);
    }


}
