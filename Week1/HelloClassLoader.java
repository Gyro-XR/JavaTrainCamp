package com.java.camp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description: week1-自定义类加载器
 * @author: xuxinrong
 * @version: [2021-03-21]
 **/
public class HelloClassLoader extends ClassLoader
{
    /**
     * 整体思路：
     * 1、读取.xlass文件，转成byte数组
     * 2、遍历数组，还原每个字节（x=255-x），得到原.class文件对应的byte数组
     * 3、调自定义类加载器的defineClass方法，转换byte数组，得到Hello.Class
     * 4、Hello.Class实例化，得到Hello实例，最后通过反射，调用其hello()方法
     */
    public static void main(String[] args)
    {
        try
        {
            Class c = new HelloClassLoader().findClass("Hello");
            c.getMethod("hello", null).invoke(c.newInstance());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        byte[] bytes = getOriginalByteArr();
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 得到转码前的原字节数组
     *
     * @return
     */
    private byte[] getOriginalByteArr()
    {
        byte[] oba = null;
        try
        {
            File xlassFile = new File("C:\\Users\\86188\\Code\\Docs\\Week1\\Hello.xlass\\Hello.xlass");
            Path path = Paths.get(xlassFile.getAbsolutePath());

            byte[] bytes = Files.readAllBytes(path);
            oba = new byte[bytes.length];

            for (int i = 0; i < bytes.length; i++)
            {
                oba[i] = (byte) (255 - bytes[i]);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return oba;
    }
}
