package com.wft.app;


import com.wft.util.HBaseUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;


public class App {
    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;

    public static void main(String[] args) throws Exception {
//        System.out.println("请选择：");
//        System.out.println("1.创建表");
//        System.out.println("2.查询");


//        // 调用HBaseUtil
        HBaseUtil.init();
        HBaseUtil.createTable("student", new String[]{"score"});
        HBaseUtil.insertData("student", "zhangsan", "score", "Computer", "77");
        String result = HBaseUtil.getData("student", "zhangsan", "score", "English");
        HBaseUtil.close();
        System.out.println(result);
    }
}