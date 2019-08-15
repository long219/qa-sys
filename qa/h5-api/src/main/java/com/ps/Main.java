package com.ps;

import java.io.IOException;
import java.util.TreeSet;


/**
 * @author 26498
 */
public class Main {

    public static void main(String[] args) throws IOException {

        //1.开启zookeeper
        //Runtime.getRuntime().exec("cmd.exe   /C   start   D:\\MyDownloads\\soft\\zookeeper-3.4.14\\zookeeper-3.4.14\\bin\\zkServer.cmd");


        //2.开启kafka
        Runtime.getRuntime().exec("cmd.exe   /C   start   D:\\MyDownloads\\soft\\kafka_2.11-2.3.0\\bin\\windows\\kafka-server-start.bat  D:\\MyDownloads\\soft\\kafka_2.11-2.3.0\\config\\server.properties");


        //3.开启redis
        Runtime.getRuntime().exec("cmd.exe   /c   start   D:\\MyDownloads\\soft\\Redis-x64-3.0.504\\redis-server.exe  D:\\MyDownloads\\soft\\Redis-x64-3.0.504\\redis.windows.conf");




        System.out.println(Math.round(Math.random()*100000));

    }
}
