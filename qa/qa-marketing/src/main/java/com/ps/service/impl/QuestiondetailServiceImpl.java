package com.ps.service.impl;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestiondetailVO;
import com.ps.exception.BusinessException;
import com.ps.mapper.QuestiondetailMapper;
import com.ps.service.QuestiondetailService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 问卷service
 *
 * @author 26498
 *
 */

@Service(version = "1.0.0")
public class QuestiondetailServiceImpl implements QuestiondetailService {


    @Autowired
    private QuestiondetailMapper questiondetailMapper;

    @Override
    public List<QuestiondetailVO> queryAll() {
        return questiondetailMapper.queryAll();
    }


    /**
     * 添加问卷
     * @param questiondetailVO
     * @return
     */
    @Override
    public MessageVO add(QuestiondetailVO questiondetailVO) {

        int check = check(questiondetailVO);
        if (check==-1){

            questiondetailMapper.add(questiondetailVO);

            return new MessageVO("0","成功");
        }

        throw  new BusinessException("1","失败");

    }

    @Override
    public List<QuestiondetailVO> queryByQuestionnaireId(Integer questionnaireId) {
        return questiondetailMapper.queryByQuestionnaireId(questionnaireId);
    }


    /**
     * 校验问卷信息
     * @param questiondetailVO
     * @return
     */
    public int check(QuestiondetailVO questiondetailVO){

        List<QuestiondetailVO> questiondetailVOS = queryAll();

        for (int i = 0; i < questiondetailVOS.size(); i++) {

            QuestiondetailVO questiondetail = questiondetailVOS.get(i);

            //判断问卷内容是否存在 ，如果不存在 返回 -1
            if (questiondetail.getName().equals(questiondetailVO.getName())){
                return  i;
            }
        }
        return  -1;
    }
}
