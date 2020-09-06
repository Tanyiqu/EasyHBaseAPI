package com.wft.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
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
     * 取数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @param colFamily colFamily
     * @param col       col
     * @throws Exception 异常
     */
    public static String getData(String tableName, String rowKey, String colFamily, String col) throws Exception {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        get.addColumn(colFamily.getBytes(), col.getBytes());
        Result result = table.get(get);
//        System.out.println(new String(result.getValue(colFamily.getBytes(), col.getBytes())));
        table.close();
        String data = new String(result.getValue(colFamily.getBytes(), col.getBytes()));
        close();
        return data;
    }

    public static void getRowKeys(String tableName) throws Exception {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));


        close();
    }

    /**
     * 根据rowKey查找数据
     *
     * @param tableName tableName
     * @param rowKey    rowKey
     * @return Result
     * @throws Exception Exception
     */
    public static Result get(String tableName, String rowKey) throws Exception {
        init();
        Get get = new Get(Bytes.toBytes(rowKey));
        Table table = connection.getTable(TableName.valueOf(tableName));
        Result result = table.get(get);
        close();
        return result;
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

}
