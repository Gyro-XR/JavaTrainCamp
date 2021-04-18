package com.example.awire;

import com.alibaba.fastjson.JSONObject;
import com.example.awire.student.Student1;
import com.example.awire.student.Student2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: Spring装配方式demo
 * @author: xuxinrong
 * @version: [2021-04-18]
 **/
public class AwireDemo
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student1 student1 = (Student1) applicationContext1.getBean("student1");
        System.out.println("通过xml显式装配的bean：");
        student1.print();

        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(Student2.class);
        System.out.println(JSONObject.toJSONString(applicationContext2));
        Student2 student2 = (Student2) applicationContext2.getBean("stu2");
        System.out.println("通过java显式装配的bean：");
        student2.print();
    }
}
