package com.example.awire;

import com.example.awire.student.Student1;
import com.example.awire.student.Student2;
import com.example.awire.student.Student3;
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
        // 方法一：xml显式装配
        ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student1 student1 = (Student1) applicationContext1.getBean("student1");
        System.out.println("通过xml显式装配的bean：" + student1.getClass());
        student1.print();

        // 方法二：Java显式装配
        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(AwireConfiguration.class);
        // 通过bean名称获取
        Student2 student2 = (Student2) applicationContext2.getBean("getStu2");
        System.out.println("通过java显式装配的bean：" + student2.getClass());
        student2.print();

        // 方法三：@Autowired隐式装配
        Student3 student3 = ApplicationContextUtil.getContext().getBean(AwireConfiguration.class).student3;
        student3.set("测试同学3", 12);
        System.out.println("通过java显式装配的bean：" + student3.getClass());
        student3.print();

    }
}
