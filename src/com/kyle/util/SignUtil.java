package com.kyle.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by Java on 2016/10/13.
 */
public class SignUtil {
    private static String token="weixinCourse";

    //对token、timestamp、nonce按字典排序
    public static boolean checkSignature(String signature,String timestamp,String nonce)  {
        boolean result=false;

        String[] array=new String[]{token,timestamp,nonce};
        Arrays.sort(array);
        //将三个字符串拼接成一个字符串
        String str=array[0].concat(array[1]).concat(array[2]);

        String sha1Str=null;
        try {
            //加密
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(str.getBytes());
            sha1Str=byte2str(digest);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(sha1Str!=null&&sha1Str.equals(signature)){
            result=true;
        }
        return result;
    }

    public static String byte2str(byte[] array){
        StringBuffer hexstr=new StringBuffer();
        String shaHex="";
        for(int i=0;i<array.length;i++){
            shaHex=Integer.toHexString(array[i]&0xFF);
            if(shaHex.length()<2){
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }
}