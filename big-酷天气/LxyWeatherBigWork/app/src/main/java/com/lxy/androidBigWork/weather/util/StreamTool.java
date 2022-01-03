package com.lxy.androidBigWork.weather.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
    public static byte[] read(InputStream inStream) throws Exception{
        // 字节数组输出流
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer,0,len);
        }
        inStream.close();
        byte[] out = outStream.toByteArray();
        outStream.close();
        return out;
    }
}
