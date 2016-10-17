package com.kyle.util;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Java on 2016/10/14.
 */
public class MysqlUtil {

    public Connection getConnection(HttpServletRequest request){
        Connection conn=null;
        String host=request.getHeader("BAE_ENV_ADDR_SQL_IP");
        String port=request.getHeader("BAE_ENV_ADDR_SQL_PORT");

        String username="24483dfd9d984d9397fa509df9ecdf7d";
        String password="e5cb788d0d5749a9b4cccf97aeb3e1f8";
        String dbName="ivEIEPTaivAkBdOPjGiC";
        String url=String.format("jdbc:mysql://%s:%s/%s",host,port,dbName);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

//    public void releaseResource(Connection conn){
//        try {
//            if (null != conn) {
//                conn.close();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void saveTextMessage(HttpServletRequest request,String openid, String content){
        Connection conn=null;
        PreparedStatement ps=null;
        String sql="insert into message_text(open_id,content,create_time) values(?,?,now())";
        try{
            conn=new MysqlUtil().getConnection(request);
            ps=conn.prepareStatement(sql);
            ps.setString(1,openid);
            ps.setString(2,content);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
