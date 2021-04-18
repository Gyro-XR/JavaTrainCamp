package com.example.awire.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Java显式配置
 * @author: xuxinrong
 * @version: [2021-04-18]
 **/
//@ComponentScan
@Configuration
public class Student2
{
    private String name;
    private int age;

    Student2(String n, int a)
    {
        this.name = name;
        this.age = age;
    }

    @Bean(name = "stu2")
    public Student2 student2()
    {
        return new Student2("测试同学2", 11);
    }

    public void print()
    {
        System.out.println("姓名="+name + ", 年龄=" + age);
    }
}
