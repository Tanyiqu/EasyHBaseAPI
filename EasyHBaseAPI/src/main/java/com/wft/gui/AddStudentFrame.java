package com.wft.gui;

import com.wft.domain.Student;
import com.wft.util.HBaseUtil;
import com.wft.util.TextUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentFrame extends JFrame {

    AddStudentFrame _this = this;
    JButton btnAdd;
    JTextField txtName;
    JTextField txtSex;
    JTextField txtMath;
    JTextField txtEnglish;

    public AddStudentFrame() throws HeadlessException {
        // title
        this.setTitle("Add");
        // location and layout
        this.setSize(165, 300);
        this.setLocation(800, 350);
        this.setLayout(new FlowLayout());
//        this.setResizable(false);

        btnAdd = new JButton("Add");
        Dimension dimension = new Dimension(80, 25);
        btnAdd.setPreferredSize(dimension);

        Dimension txtDimension = new Dimension(100, 25);
        txtName = new JTextField();
        txtName.setPreferredSize(txtDimension);
        txtSex = new JTextField();
        txtSex.setPreferredSize(txtDimension);
        txtMath = new JTextField();
        txtMath.setPreferredSize(txtDimension);
        txtEnglish = new JTextField();
        txtEnglish.setPreferredSize(txtDimension);
        this.add(new JLabel("Name"));
        this.add(txtName);
        this.add(new JLabel("Sex"));
        this.add(txtSex);
        this.add(new JLabel("Math"));
        this.add(txtMath);
        this.add(new JLabel("English"));
        this.add(txtEnglish);
        this.add(btnAdd);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get inputs
                String name = txtName.getText();
                String sex = txtSex.getText();
                String math = txtMath.getText();
                String english = txtEnglish.getText();
                // ensure strings not empty
                if (TextUtil.empty(name, sex, math, english)) {
                    System.out.println("do not input empty!");
                    return;
                }
                try {
                    HBaseUtil.save(new Student(name, sex, math, english));
                    _this.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });
    }


}
