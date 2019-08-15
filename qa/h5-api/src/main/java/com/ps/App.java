package com.ps;

import cn.gjing.lock.core.EnableRedisLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 * @author 26498
 */

@SpringBootApplication
@EnableRedisLock
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
