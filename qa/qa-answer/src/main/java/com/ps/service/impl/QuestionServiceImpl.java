package com.ps.service.impl;

import com.ps.domain.MessageVO;
import com.ps.domain.QuestionVO;
import com.ps.exception.BusinessException;
import com.ps.mapper.QuestionMapper;
import com.ps.service.QuestionService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 26498
 */
@Service(version = "1.0.0")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionVO> queryAll() {
        return questionMapper.queryAll();
    }

    @Override
    public QuestionVO queryById(Integer id) {
        return questionMapper.queryById(id);
    }

    @Override
    public List<QuestionVO> queryQuestionDetail(Integer questionId) {
        return questionMapper.queryQuestionDetail(questionId);
    }

    @Override
    public MessageVO addQuestion(QuestionVO questionVO) {
        int check = check(questionVO);

        if (check==-1){
            questionMapper.addQuestion(questionVO);

            return new MessageVO("0","成功");
        }
        throw  new BusinessException("1","失败");
    }


    /**
     * 根据关键字查询
     * @param name
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @Override
    public  MessageVO  vagueQuery(String name) throws SolrServerException, IOException {
       // 构建一个solr 客户端
       HttpSolrClient client = new HttpSolrClient.Builder("http://192.168.3.99:8983/solr").build();

       // 准备参数
       Map<String, String> params = new HashMap<>();
       params.put("q", "questionName:"+name);

       /*params.put("start", "0");
       params.put("rows", "10");*/

       // 查询
       // 第一个参数 coreName
        QueryResponse response = client.query("qa", new MapSolrParams(params));


        List<QuestionVO> questionVO = response.getBeans(QuestionVO.class);

        if (questionVO.size()!=0){

            return new MessageVO("0","成功",questionVO);
        }

        throw  new BusinessException("1","失败");

    }

    /**
     * 校验问题是否存在
     * @param questionVO
     * @return
     */
    public int check(QuestionVO questionVO){

        List<QuestionVO> questionVOS = queryAll();

        //判断问题内容是否存在 ，如果不存在 返回 -1
        for (int i = 0; i < questionVOS.size(); i++) {

            QuestionVO questionVO1 = questionVOS.get(i);

            if (questionVO1.getContent().equals(questionVO.getContent())){
                return  i;
            }
        }
        return  -1;
        
    }
}
