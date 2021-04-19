package com.example.awire;

import com.example.awire.student.Student2;
import com.example.awire.student.Student3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Java configuration装配bean对象
 * @author: xuxinrong
 * @version: [2021-04-19]
 **/
@ComponentScan
@Configuration
public class AwireConfiguration
{
    // 隐式装配
    @Autowired
    public Student3 student3;

    @Bean
    public Student2 getStu2()
    {
        return new Student2("测试同学2", 11);
    }

}
