package com.ps.service.impl;

import com.ps.domain.AnswerVO;
import com.ps.domain.MessageVO;
import com.ps.domain.QuestionVO;
import com.ps.exception.BusinessException;
import com.ps.mapper.AnswerMapper;
import com.ps.mapper.QuestionMapper;
import com.ps.service.AnswerService;
import com.ps.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 26498
 */

@Service(version = "1.0.0")
public class AnswerServiceImpl implements AnswerService {


    /**
     * 回答的mapper
     */
    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 问题的mapper
     */
    @Autowired
    private QuestionMapper questionMapper;


    /**
     * 会员service
     */
    @Reference(version = "1.0.0")
    private MemberService memberService;


    /**
     * 注入消息队列
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public List<AnswerVO> queryAll() {
        return answerMapper.queryAll();
    }

    @Override
    public MessageVO addAnswer(AnswerVO answerVO) {

        int check = check(answerVO);
        if (check==-1){
            answerMapper.addAnswer(answerVO);

            return new MessageVO("0","成功");
        }

        throw  new BusinessException("1","失败");
    }


    @Override
    public MessageVO updateAnswer(AnswerVO answerVO) {

        int checkType = checkType(answerVO);
        if (checkType ==-1){

            answerMapper.updateAnswer(answerVO);

            //根据问题id 获取问题信息
            QuestionVO questionVO = questionMapper.queryById(answerVO.getQuestion_id());


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
                //创建生产者 (kafka)
                //第一个参数 提问者的id
                //第二个参数 回答者的id
                //第三个参数 赠送的积分
                kafkaTemplate.send("answer","answerKey",questionVO.getUser_id()+","+answerVO.getUser_id()+","+questionVO.getReward());

            }else{

                //扣除提问者积分
                memberService.deductPoint(questionVO.getUser_id(), -(questionVO.getReward()));

                //赠送被采纳者积分
                memberService.deductPoint(answerVO.getUser_id(), questionVO.getReward());
            }

            return new MessageVO("0","成功");
        }

        throw  new BusinessException("1","失败");

    }



    /**
     * 校验回答是否存在
     * @param answerVO
     * @return
     */
    public int check(AnswerVO answerVO){

        List<AnswerVO> answerVOS = queryAll();

        //判断回答内容是否存在 ，如果不存在 返回 -1
        for (int i = 0; i < answerVOS.size(); i++) {

            AnswerVO answerVO1 = answerVOS.get(i);

            if (answerVO1.getContent().equals(answerVO.getContent())){
                return  i;
            }
        }
        return  -1;
    }


    /**
     * 校验回答是否存在
     * @param answerVO
     * @return
     */
    public int checkType(AnswerVO answerVO){

        List<AnswerVO> answerVOS = queryAll();

        //判断是否被采纳了 ，如果没有就返回 -1
        for (int i = 0; i < answerVOS.size(); i++) {

            AnswerVO answerVO1 = answerVOS.get(i);

            if (answerVO1.getType().equals(answerVO.getType())){
                return  i;
            }
        }
        return  -1;
    }

}
