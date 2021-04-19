package com.example.awire.student;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description: 实体类
 * @author: xuxinrong
 * @version: [2021-04-19]
 **/
@Data
@Component
public class Student3
{
    private String name;
    private int age;

    public void set(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void print()
    {
        System.out.println("姓名=" + name + ", 年龄=" + age);
    }
}
