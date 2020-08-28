package com.wft.app;


import com.wft.util.HBaseUtil;

public class App {
    public static void main(String[] args) throws Exception {
//        System.out.println("请选择：");
//        System.out.println("1.创建表");
//        System.out.println("2.查询");
//        // 调用HBaseUtil
        HBaseUtil.init();
        HBaseUtil.createTable("student", new String[]{"score"});
        HBaseUtil.insertData("student", "吴硕", "score", "Computer", "77");
        String result = HBaseUtil.getData("student", "吴硕", "score", "Computer");
        HBaseUtil.close();
        System.out.println(result);
    }
}