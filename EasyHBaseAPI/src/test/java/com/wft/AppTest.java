package com.wft;

import com.wft.domain.Student;
import com.wft.util.HBaseUtil;
import org.junit.Test;

import java.util.List;


/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test() throws Exception {
        Student student = HBaseUtil.get("w5");
        System.out.println(student);
    }

    @Test
    public void testAll() throws Exception {
        List<Student> students = HBaseUtil.getAll();
        for (Student s : students) {
            System.out.println(s);
        }
    }

}
