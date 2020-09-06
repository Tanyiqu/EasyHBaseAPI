package com.wft.util;

import com.wft.domain.Student;
import org.apache.hadoop.hbase.client.Result;

public class AppUtil {

    public static Student get(String name) throws Exception{
        Student student = new Student();
        Result result = HBaseUtil.get("student", name);

        return student;
    }


    public static void save(Student student){

    }

}
