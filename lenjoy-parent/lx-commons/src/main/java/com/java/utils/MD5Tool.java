package com.java.utils;

import java.security.MessageDigest;


/**
 * description：MD5加密工具类
 * author：丁鹏
 * date：16:41
 */
public class MD5Tool {
    //0~15
    private static final String[] digital = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    private static String initMD5(String txt) throws Exception {
        //明文
        //String txt = "123abc";
        //获取封装了MD5算法的对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        if(txt==null){
            throw new Exception("亲，您眼瞎嘛，明文为null");
        }
        byte[] bytes = md5.digest(txt.getBytes("UTF-8"));
        //bytes字节数组中的每个数组算出两个不同的下标(0~15)
        StringBuilder miWen = new StringBuilder("");
        for (byte temp : bytes) {
            int num = temp;
            if(num<0){
                num+=256;
            }
            int index1 = num/16;
            int index2 = num%16;
            miWen.append(digital[index1]).append(digital[index2]);
        }
        return miWen.toString();
    }

    /**
     * 最终的MD5加密
     * @param txt
     * @return
     * @throws Exception
     */
    public static String MD5(String txt) throws Exception{
        return initMD5(initMD5(initMD5(initMD5(txt)+"dingP")+"dingP")+"dingP");
    }

    public static void main(String[] args) throws Exception {
        String miWen= MD5Tool.MD5("123456");
        System.out.println(miWen);
    }

}
