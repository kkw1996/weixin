package com.kyle.util;

import com.kyle.pojo.Token;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import javax.net.ssl.TrustManager;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by Java on 2016/10/25.
 */
public class CommonUtil {
    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr) throws Exception{
        SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
        TrustManager[] tm={new MyX509TrustManager()};
        sslContext.init(null,tm,new java.security.SecureRandom());
        SSLSocketFactory ssf=sslContext.getSocketFactory();

        URL url=new URL(requestUrl);
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setSSLSocketFactory(ssf);
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
    public final static String token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static Token getAcessToken(String appid, String appsecret) throws Exception {
        Token token=null;
        String requestUrl=token_url.replace("APPID",appid).replace("APPSECRET",appsecret);
        String jsonString =CommonUtil.httpsRequest(requestUrl,"GET",null);
        JSONObject jsonObject=JSONObject.fromObject(jsonString);
        String accessToken=jsonObject.getString("access_token");
        int expiresIn=jsonObject.getInt("expires_in");
        token=new Token();
        token.setAccess_token(accessToken);
        token.setExpires_in(expiresIn);
        return token;
    }
}
