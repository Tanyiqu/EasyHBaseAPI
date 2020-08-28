package com.wft.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;


/**
 * HBase连接工具类
 */
public class HBaseUtil {
    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;


    public static void init() throws Exception {
        System.out.println("初始化Hbase");
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
        connection = ConnectionFactory.createConnection(configuration);
        admin = connection.getAdmin();
    }

    /**
     * 创建表
     *
     * @param name      表名
     * @param colFamily 列
     */
    public static void createTable(String name, String[] colFamily) throws IOException {
        TableName tableName = TableName.valueOf(name);
        if (admin.tableExists(tableName)) {
            System.out.println("talbe is exists!");
        } else {
            TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(tableName);
            for (String str : colFamily) {
                ColumnFamilyDescriptor family = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(str)).build();
                tableDescriptor.setColumnFamily(family);
            }
            admin.createTable(tableDescriptor.build());
        }
    }


    /**
     * 插入数据
     *
     * @param tableName 表名
     * @param rowKey
     * @param colFamily
     * @param col
     * @param val
     * @throws IOException 异常
     */
    public static void insertData(String tableName, String rowKey, String colFamily, String col, String val) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
        table.put(put);
        table.close();
    }

    /**
     * 取数据
     *
     * @param tableName 表名
     * @param rowKey
     * @param colFamily
     * @param col
     * @throws IOException 异常
     */
    public static String getData(String tableName, String rowKey, String colFamily, String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        get.addColumn(colFamily.getBytes(), col.getBytes());
        Result result = table.get(get);
//        System.out.println(new String(result.getValue(colFamily.getBytes(), col.getBytes())));
        table.close();
        return new String(result.getValue(colFamily.getBytes(), col.getBytes()));
    }

    /**
     * 关闭连接
     */
    public static void close() throws IOException {
        if (admin != null) {
            admin.close();
        }
        if (null != connection) {
            connection.close();
        }
    }

}
