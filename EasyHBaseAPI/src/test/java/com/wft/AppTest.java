package com.wft;

import com.wft.util.HBaseUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test() throws Exception{
        String result = HBaseUtil.getData("student", "z3", "score", "Math");
        System.out.println(result);
    }
}
