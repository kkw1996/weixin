package com.kyle.util;

import com.kyle.pojo.Token;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Java on 2016/11/14.
 */
public class CommonUtilHttp {
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr) throws Exception{

        URL url=new URL(requestUrl);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        //往服务器端写内容
        if(null!=outputStr){
            OutputStream os=conn.getOutputStream();
            os.write(outputStr.getBytes("utf-8"));
            os.close();
        }

        //读取服务器内容
        InputStream is=conn.getInputStream();
        InputStreamReader isr=new InputStreamReader(is,"utf-8");
        BufferedReader br=new BufferedReader(isr);
        StringBuffer buffer=new StringBuffer();
        String line=null;
        while((line=br.readLine())!=null){
            buffer.append(line);
        }
        return buffer.toString();
    }

    public static String urlEncodeUTF8(String source) throws UnsupportedEncodingException {
        String result=source;
        result= URLEncoder.encode(source,"UTF-8");
        return result;
    }
}
