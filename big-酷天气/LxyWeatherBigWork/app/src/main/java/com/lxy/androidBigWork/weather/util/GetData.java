package com.lxy.androidBigWork.weather.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


//根据url 获取数据
public class GetData {

    public static String getJson(String path) throws Exception {
        URL url = new URL(path);
        System.out.println("url:"+ url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            // 获取输入流
            InputStream in = conn.getInputStream();
            byte[] data = StreamTool.read(in);
            return new String(data, "UTF-8");
        }
        return null;
    }

}
