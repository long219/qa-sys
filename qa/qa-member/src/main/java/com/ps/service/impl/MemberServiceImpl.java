package com.ps.service.impl;

import com.ps.domain.IntegralVO;
import com.ps.domain.MemberVO;
import com.ps.domain.MessageVO;
import com.ps.exception.BusinessException;
import com.ps.mapper.IntegralMapper;
import com.ps.mapper.MemberMapper;
import com.ps.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 26498
 */
@Service(version = "1.0.0")
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private IntegralMapper integralMapper;


    /**
     * 注入消息队列
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public List<MemberVO> queryAll() {
        return memberMapper.queryAll();
    }

    @Override
    public List<MemberVO> queryById(Integer id) {
        return memberMapper.queryById(id);
    }

    @Override
    @Transactional
    public MessageVO deductPoint(Integer id, Integer point_number) {

        if (id!=null && point_number!=null){

            //赠送积分
            memberMapper.deductPoint(id,point_number);

            IntegralVO integralVO = new IntegralVO(point_number, id, "1");

            //添加流水表
            integralMapper.addIntegral(integralVO);

            System.out.println("----流水表主键----"+integralVO.getId());

            return new MessageVO("0","成功",integralVO.getId());
        }
        throw  new BusinessException("1","失败") ;
    }



    @Override
    public MessageVO add(MemberVO memberVO) {

        int check = check(memberVO);

        if (check==-1){

            memberMapper.add(memberVO);


            //创建一个日期
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_MONTH, 1);

            //此时获取的是当前时间往后推一天的日期
            Date date = calendar.getTime();

            //转为毫秒数
            long dateTime = date.getTime();

            //获取当前时间的毫秒数
            long timeMillis = System.currentTimeMillis();

            if (dateTime>timeMillis){
                //赠送积分
                try {
                    kafkaTemplate.send("member","user",memberVO.getId().toString());
                }catch (Exception e){
                    log.info("中间键出错了！");
                }
            }

            return new MessageVO("0","成功");
        }

        throw  new BusinessException("1","失败") ;
    }


    /**
     * 校验会员信息
     * @param memberVO
     * @return
     */
    public int check(MemberVO memberVO){
        List<MemberVO> memberVOS = queryAll();

        for (int i = 0; i < memberVOS.size(); i++) {

            MemberVO member = memberVOS.get(i);

            //判断姓名是否存在 ，如果不存在 返回 -1
            if (member.getName().equals(memberVO.getName())){
                return  i;
            }
        }
        return  -1;
    }
}
