package com.ps;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.*;

public class Mani {

    public static void main(String[] args) throws IOException, SolrServerException, InterruptedException {





        //创建一个日期
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();

        //此时获取的是当前时间往前推三天的日期
        System.out.println(date);

        long time = System.currentTimeMillis();

        if (date.getTime()>time){
            System.out.println(date.getTime() +"-----------"+time);
        }



        /*
       // 构建一个solr 客户端
       HttpSolrClient client = new HttpSolrClient.Builder("http://192.168.3.99:8983/solr").build();

       // 准备参数
       Map<String, String> params = new HashMap<>();
       params.put("q", "questionName:什");
       *//*params.put("start", "0");
       params.put("rows", "10");*//*

       // 查询
       // 第一个参数 coreName
        QueryResponse response = client.query("qa", new MapSolrParams(params));


        List<QuestionVO> questionVO = response.getBeans(QuestionVO.class);


        for (int i = 0; i < questionVO.size(); i++) {
            QuestionVO questionVO1 = questionVO.get(i);
            System.out.println(questionVO1.toString());
        }*/

    }


}
