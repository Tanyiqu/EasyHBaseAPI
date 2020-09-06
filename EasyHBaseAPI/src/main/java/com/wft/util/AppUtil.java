package com.wft.util;

import com.alibaba.lindorm.client.core.utils.Bytes;
import com.wft.domain.Student;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtil {

    /**
     * get Student
     *
     * @param name name
     * @return Student
     * @throws Exception Exception
     */
    public static Student get(String name) throws Exception {
        Result r = HBaseUtil.get("student", name);
        if (r == null) {
            return null;
        }
        String sex = new String(r.getValue(Bytes.toBytes("sex"), Bytes.toBytes("")));
        String math = new String(r.getValue(Bytes.toBytes("score"), Bytes.toBytes("Math")));
        String english = new String(r.getValue(Bytes.toBytes("score"), Bytes.toBytes("English")));
        return new Student(name, sex, math, english);
    }


    /**
     * save student
     *
     * @param student student
     * @throws Exception Exception
     */
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
        System.out.println("save finished");
    }


}
