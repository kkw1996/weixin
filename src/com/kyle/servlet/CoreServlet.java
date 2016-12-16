package com.kyle.servlet;

import com.kyle.service.CoreService;
import com.kyle.util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Java on 2016/10/13.
 */
@WebServlet(name = "CoreServlet",urlPatterns = {"/CoreServlet"})
public class CoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("哈哈哈");
        //请求校验
        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        PrintWriter out=response.getWriter();
        if(SignUtil.checkSignature(signature,timestamp,nonce)){
            //消息处理
            String respXML= CoreService.processRequest(request);
            out.write(respXML); //这个表示回复给微信服务器 嗯 是的
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");

        PrintWriter out=response.getWriter();

        if(SignUtil.checkSignature(signature,timestamp,nonce)){
            out.write(echostr);
        }
        out.close();
    }
}
