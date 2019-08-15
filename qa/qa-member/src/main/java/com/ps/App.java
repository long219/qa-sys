package com.ps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Hello world!
 *
 * @author 26498
 */

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

/*
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    public void testSend(){

        // 第一个参数为topic 主题， 不存在时会自动创建
        // 第二个参数为key
        // 第三个参数为data 值， 这里传foo, 消费者选择指定groupId消费

         kafkaTemplate.send("panshi", "hello...", "foo");
    }*/



}
