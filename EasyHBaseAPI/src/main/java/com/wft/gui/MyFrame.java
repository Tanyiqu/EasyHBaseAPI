package com.wft.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    JButton btnShowStudent;
    JButton btnAddStudent;
    JButton btnClose;

    public MyFrame() throws HeadlessException {
        initFrame();
    }

    void initFrame() {
        // title
        this.setTitle("Easy HBase API");
        // location
        this.setSize(240, 300);
        this.setLocation(800, 350);
        // layout
        this.setLayout(new FlowLayout());
        this.setResizable(false);

        Dimension dimension = new Dimension(200, 25);
        btnShowStudent = new JButton("Show Student");
        btnShowStudent.setPreferredSize(dimension);
        btnAddStudent = new JButton("Add Student");
        btnAddStudent.setPreferredSize(dimension);
        btnClose = new JButton("Close");
        btnClose.setPreferredSize(dimension);

        // add widgets
        this.add(btnShowStudent);
        this.add(btnAddStudent);
        this.add(btnClose);

        addListeners();

        // close on exit
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // visible
        this.setVisible(true);
    }

    void addListeners() {

        // show
        btnShowStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowStudentFrame();
            }
        });

        // add
        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentFrame();
            }
        });

        // close
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
