package com.wft;

import com.wft.domain.Student;
import com.wft.util.AppUtil;
import com.wft.util.HBaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test() throws Exception {
//        Result r = HBaseUtil.get("student", "z3");
//
//        for (Cell cell : r.listCells()) {
//            System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
//            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
//        }
        Student student = AppUtil.get("z3");
        System.out.println(student);

    }
}
