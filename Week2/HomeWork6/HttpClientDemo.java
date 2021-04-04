package com.camp.http.demo.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @description: HttpClientDemo类
 * @author: xuxinrong
 * @version: [2021-04-04]
 **/
public class HttpClientDemo
{
    public static void main(String[] args)
    {
        String url = "http://localhost:8801";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            System.out.println("url = " + url + ", res = " + result);
        } catch (Exception e)
        {
            System.out.println("HttpClientDemo doInvoke error");
            e.printStackTrace();
        }
    }

}
