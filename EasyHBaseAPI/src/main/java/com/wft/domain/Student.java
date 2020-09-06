package com.wft.domain;

import java.io.Serializable;

public class Student implements Serializable {

    String name;
    String sex;
    String math;
    String english;

    public Student() {
    }

    public Student(String name, String sex, String math, String english) {
        this.name = name;
        this.sex = sex;
        this.math = math;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", sex='" + sex + '\'' +
                ", math='" + math + '\'' +
                ", english='" + english + '\'' +
                '}';
    }
}
