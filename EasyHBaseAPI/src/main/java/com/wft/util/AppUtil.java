package com.wft.util;

import com.alibaba.lindorm.client.core.utils.Bytes;
import com.wft.domain.Student;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;

public class AppUtil {

    /**
     * get Student
     *
     * @param name name
     * @return Student
     * @throws Exception Exception
     */
    public static Student get(String name) throws Exception {
        Student student = new Student();
        Result result = HBaseUtil.get("student", name);
        for (Cell cell : result.listCells()) {
            System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }

        student.setName(name);

        return student;
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
