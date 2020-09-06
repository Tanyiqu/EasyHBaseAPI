package com.wft.util;

import com.wft.domain.Student;
import org.apache.hadoop.hbase.client.Result;

public class AppUtil {

    public static Student get(String name) throws Exception {
        Student student = new Student();
        Result result = HBaseUtil.get("student", name);

        return student;
    }


    public static void save(Student student) throws Exception {
        
        if (!HBaseUtil.tableIsExist("student")) {
            System.out.println("Table is not exist");
            // create table
            HBaseUtil.createTable("student", new String[]{"sex", "score"});
            System.out.println("create finished");
        }
        HBaseUtil.insertData("student", student.getName(), "sex", "", student.getSex());
        HBaseUtil.insertData("student", student.getName(), "score", "Math", student.getMath());
        HBaseUtil.insertData("student", student.getName(), "score", "English", student.getEnglish());
        System.out.println("insert finished");
    }

}
