package com.example.awire.student;

import lombok.Data;

/**
 * @description: 实体类
 * @author: xuxinrong
 * @version: [2021-04-18]
 **/
@Data
public class Student2
{
    private String name;
    private int age;

    public Student2(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void print()
    {
        System.out.println("姓名=" + name + ", 年龄=" + age);
    }
}
