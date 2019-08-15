package com.ps.config;

import com.ps.domain.QuestionVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 26498
 */

@Component
public class SolrUtils {

    private SolrClient solrClient;
    
    private SolrQuery solrQuery;

    @Autowired
    public SolrUtils(SolrClient solrClient) {
        this.solrClient = solrClient;
        solrQuery = new SolrQuery();
    }

    public SolrUtils() {
    }

    /**
     * 向solr文档中添加对象
     * @param data
     * @throws IOException
     * @throws SolrServerException
     */
    public void addSolrPO(QuestionVO data) throws IOException, SolrServerException {
        solrClient.addBean(data);
        solrClient.commit();
    }
 
    /**
     * solr查询
     * @param key  关键字
     * @param page  
     * @param row
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public List<QuestionVO> queryList(String key, int page, int  row) throws IOException, SolrServerException {

        List<QuestionVO> list=new ArrayList();

        solrQuery.setQuery(key);
        solrQuery.setStart(page);
        solrQuery.setRows(row);

        //设置高亮字段
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("answerName");
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        QueryResponse query = solrClient.query(solrQuery);
        
        // 文档结果集
        SolrDocumentList docs = query.getResults();

        //问题实例
        QuestionVO questionVO;

        for (SolrDocument s : docs) {
            questionVO = new QuestionVO();
            questionVO.setId((Integer) s.getFirstValue("mId"));
            questionVO.setReward((Integer) s.getFieldValue("answerName"));
            list.add(questionVO);
        }
        return list;
    }
 
    /**
     * 删除
     * @throws IOException
     * @throws SolrServerException
     */
    public void delDoc() throws IOException, SolrServerException {
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
    }
}
