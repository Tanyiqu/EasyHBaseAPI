package com.wft.util;

import com.wft.domain.Student;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * HBase连接工具类
 */
public class HBaseUtil {
    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;

    /**
     * 初始化
     *
     * @throws Exception 异常
     */
    public static void init() throws Exception {
        configuration = HBaseConfiguration.create();
//        configuration.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
        connection = ConnectionFactory.createConnection(configuration);
        admin = connection.getAdmin();
    }

    /**
     * return true if table is exist
     *
     * @param name tableName
     * @return return true if table is exist
     */
    public static boolean tableIsExist(String name) throws Exception {
        init();
        TableName tableName = TableName.valueOf(name);
        boolean flag = admin.tableExists(tableName);
        close();
        return flag;
    }

    /**
     * 创建表
     *
     * @param name      表名
     * @param colFamily 列
     * @throws IOException 异常
     */
    public static void createTable(String name, String[] colFamily) throws Exception {
        init();
        TableName tableName = TableName.valueOf(name);
        if (admin.tableExists(tableName)) {
            System.out.println("table is exists!");
        } else {
            TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(tableName);
            for (String str : colFamily) {
                ColumnFamilyDescriptor family = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(str)).build();
                tableDescriptor.setColumnFamily(family);
            }
            admin.createTable(tableDescriptor.build());
        }
        close();
    }


    /**
     * 插入数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @param colFamily colFamily
     * @param col       col
     * @param val       val
     * @throws IOException 异常
     */
    public static void insertData(String tableName, String rowKey, String colFamily, String col, String val) throws Exception {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
        table.put(put);
        table.close();
        close();
    }


    /**
     * 关闭连接
     *
     * @throws IOException 异常
     */
    public static void close() throws IOException {
        if (admin != null) {
            admin.close();
        }
        if (null != connection) {
            connection.close();
        }
    }


    /**
     * save student
     *
     * @param student student
     * @throws Exception Exception
     */
    public static void save(Student student) throws Exception {
        if (!tableIsExist("student")) {
            System.out.println("Table is not exist");
            // create table
            createTable("student", new String[]{"sex", "score"});
            System.out.println("create finished");
        }
        insertData("student", student.getName(), "sex", "", student.getSex());
        insertData("student", student.getName(), "score", "Math", student.getMath());
        insertData("student", student.getName(), "score", "English", student.getEnglish());
        System.out.println("save finished");
    }

    /**
     * get Student
     *
     * @param name name
     * @return Student
     * @throws Exception Exception
     */
    public static Student get(String name) throws Exception {
        init();
        Get get = new Get(Bytes.toBytes(name));
        Table table = connection.getTable(TableName.valueOf("student"));
        Result r = table.get(get);
        if (r == null) {
            return null;
        }
        String sex = new String(r.getValue(Bytes.toBytes("sex"), Bytes.toBytes("")));
        String math = new String(r.getValue(Bytes.toBytes("score"), Bytes.toBytes("Math")));
        String english = new String(r.getValue(Bytes.toBytes("score"), Bytes.toBytes("English")));
        close();
        return new Student(name, sex, math, english);
    }


    /**
     * get all students
     *
     * @return students
     * @throws Exception Exception
     */
    public static List<Student> getAll() throws Exception {
        List<Student> students = new ArrayList<>();
        init();
        Table table = connection.getTable(TableName.valueOf("student"));
        ResultScanner scan = table.getScanner(new Scan());
        for (Result rst : scan) {
            String rowKey = Bytes.toString(rst.getRow());
            students.add(get(rowKey));
        }
        scan.close();
        close();
        return students;
    }

}
