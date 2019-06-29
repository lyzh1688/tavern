package com.tuoyou.tavern.invoice.common.libs.utils;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 刘悦之 on 2019/6/28.
 */
public class ImageUtils {

    // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    public static String imageToBase64ByLocal(String imgFile) {
        try {
            InputStream in = null;
            byte[] data = null;
            // 读取图片字节数组
            try {
                in = new FileInputStream(imgFile);
                data = new byte[in.available()];
                in.read(data);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            // 返回Base64编码过的字节数组字符串
//            System.out.println(encoder.encode(data));
            return encoder.encode(data);
        }
        catch (Exception e){
            return "";
        }
    }

}
