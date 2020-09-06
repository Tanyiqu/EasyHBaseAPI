package com.wft.gui;

import com.wft.domain.Student;
import com.wft.util.HBaseUtil;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ShowStudentFrame extends JFrame {

    public ShowStudentFrame() throws HeadlessException {
        this.setTitle("Show");
        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());

        // 表头（列名）
        Object[] columnNames = {"姓名", "性别", "数学", "英语"};
        List<Student> students = null;
        try {
            students = HBaseUtil.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert students != null;
        int len = students.size();
        Object[][] rowData = new Object[len][4];

        for (int i = 0; i < len; i++) {
            rowData[i][0] = students.get(i).getName();
            rowData[i][1] = students.get(i).getSex();
            rowData[i][2] = students.get(i).getMath();
            rowData[i][3] = students.get(i).getEnglish();
        }

        // 创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(250, 300));
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
