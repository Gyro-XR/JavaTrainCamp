package com.example.awire.student;

import lombok.Data;

/**
 * @description: xml显式配置
 * @author: xuxinrong
 * @version: [2021-04-18]
 **/
@Data
public class Student1
{
    private String name;
    private int age;

    public void print()
    {
        System.out.println("姓名="+name + ", 年龄=" + age);
    }
}
