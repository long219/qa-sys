package com.ps;

import com.ps.config.SolrUtils;
import com.ps.domain.QuestionVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SolrTest {
 
    private Logger logger = LoggerFactory.getLogger(SolrTest.class);
 
    //@Autowired
    private SolrUtils solrUtils= new SolrUtils();

    @Autowired
    private SolrClient solrClient;
 
    @Test
    public void queryList() {
        try {
            List<QuestionVO> solrQuestionVO = solrUtils.queryList("*:*", 0, 10);
            logger.info("搜索结果集长度---------->size=" + solrQuestionVO.size());
            for (QuestionVO s : solrQuestionVO) {
                logger.info(s.toString());
            }
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
            logger.info("搜索失败------>e=" + e.toString());
        }
    }





    @Test
    public void findPage() throws IOException, SolrServerException {
        /**
         * 集群版CloudSolrClient继承自SolrClient 进行强转
         * 单机版不需要直接使用solrClient
         */
        CloudSolrClient cloudSolrClient = (CloudSolrClient) solrClient;
        //设置默认的操作实例
        cloudSolrClient.setDefaultCollection("qa");
        //设置查找的参数
        SolrQuery query = new SolrQuery();

        query.setQuery("*:*");
        query.setStart(1);
        query.setRows(20);
        //执行查找
        QueryResponse response = cloudSolrClient.query(query);

        SolrDocumentList documentList = response.getResults();

        for (SolrDocument entries : documentList) {
            String questionName = (String) entries.getFieldValue("questionName");
            String answerName = (String) entries.getFieldValue("answerName");
            String id = (String) entries.getFieldValue("mId");
            System.out.println(id + "-->" + questionName+"----"+answerName);
        }
    }


}

