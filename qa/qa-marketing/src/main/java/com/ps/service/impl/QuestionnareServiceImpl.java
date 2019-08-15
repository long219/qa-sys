package com.ps.service.impl;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionnareVO;
import com.ps.exception.BusinessException;
import com.ps.mapper.QuestionnareMapper;
import com.ps.service.QuestionnareService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 26498
 */
@Service(version = "1.0.0")
public class QuestionnareServiceImpl implements QuestionnareService {

    @Autowired
    private QuestionnareMapper questionnareMapper;

    @Override
    public List<QuestionnareVO> queryAll() {
        return questionnareMapper.queryAll();
    }

    @Override
    public MessageVO add(QuestionnareVO questionnareVO) {

        int check = check(questionnareVO);
        if (check==-1){
            questionnareMapper.addQuestionnare(questionnareVO);

            return new MessageVO("0","成功");
        }

        throw  new BusinessException("1","失败");
    }


    /**
     * 校验问卷标题信息
     * @param questionnareVO
     * @return
     */
    public int check(QuestionnareVO questionnareVO){

        List<QuestionnareVO> questionnareVOS = queryAll();

        for (int i = 0; i < questionnareVOS.size(); i++) {

            QuestionnareVO questionnare = questionnareVOS.get(i);

            //判断问卷标题是否存在 ，如果不存在 返回 -1
            if (questionnare.getName().equals(questionnareVO.getName())){
                return  i;
            }
        }
        return  -1;
    }
}
