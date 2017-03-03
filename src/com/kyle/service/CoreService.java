package com.kyle.service;



import com.kyle.message_resp.*;
import com.kyle.util.FacePlusPlusUtil;
import com.kyle.util.MessageUtil;
import com.kyle.util.MysqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Java on 2016/10/9.
 * 邏輯處理，就是你想怎麽將請求消息進行處理就怎麽處理，按照你的意願返回
 */
public class CoreService {
    public static String processRequest(HttpServletRequest request){
        String respXML=null;
        TextMessage tm=new TextMessage(); //想要返回返回的文本對象
        //解析请求
        try {
            HashMap<String,String> requestMap= MessageUtil.parseXml(request);
            String fromUserName=requestMap.get("FromUserName");
            String toUserName=requestMap.get("ToUserName");
            String msgType=requestMap.get("MsgType");

            tm.setFromUserName(toUserName);
            tm.setToUserName(fromUserName);
            tm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            tm.setCreateTime(new Date().getTime());
            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
                //tm.setContent("您发送的是文本消息");
                //将文本保存到BAE数据库
                String content=requestMap.get("Content");
                //MysqlUtil.saveTextMessage(request,fromUserName,content);
                //tm.setContent("BAE MYSQL 已存");
                if(content.equals("歌曲")){
                    Music music=new Music();
                    music.setTitle("boom boom pow");
                    music.setDescription("Black Eyed Pea");
                    music.setMusicUrl("http://kyleweixin.duapp.com/weixin_war/music/boom_boom_pow.mp3");

                    music.setHQMusicUrl("http://kyleweixin.duapp.com/weixin_war/music/boom_boom_pow.mp3");

                    MusicMessage mm=new MusicMessage();
                    mm.setFromUserName(toUserName);
                    mm.setToUserName(fromUserName);
                    mm.setCreateTime(new Date().getTime());
                    mm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
                    mm.setMusic(music);

                    respXML=MessageUtil.messageToXML(mm);
                }
                else if("2048".equals(content)){

                    Article article=new Article();
                    article.setTitle("挑战2048");
                    article.setDescription("这张图是假的。By the way. 反正我是没到过2048 :)");
                    article.setPicUrl("http://kyleweixin.duapp.com/weixin_war/image/2048.jpg");
                    article.setUrl("http://kyleweixin.duapp.com/weixin_war/2048/index.html");

                    List<Article> articles=new ArrayList<>();
                    articles.add(article);

                    NewsMessage nm=new NewsMessage();
                    nm.setFromUserName(toUserName);
                    nm.setToUserName(fromUserName);
                    nm.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    nm.setCreateTime(new Date().getTime());
                    nm.setArticleCount(articles.size());
                    nm.setArticles(articles);

                    respXML=MessageUtil.messageToXML(nm);
                }else{
                    tm.setContent("您发送的是文本消息，" +
                            "" +
                            "" +
                            "试着发送“2048”看看。");
                    respXML=MessageUtil.messageToXML(tm);
                }
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
                String imageUrl=requestMap.get("PicUrl");
                String result=FacePlusPlusUtil.detectFace(imageUrl);
                if(result!=null){
                    tm.setContent(FacePlusPlusUtil.detectFace(imageUrl));
                }else{
                    tm.setContent("无法识别图片，请换一张。");
                }


                respXML=MessageUtil.messageToXML(tm);
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
                tm.setContent("您发送的是视频消息");
                respXML=MessageUtil.messageToXML(tm);
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
                tm.setContent("您发送的是语音消息");
                respXML=MessageUtil.messageToXML(tm);
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
                tm.setContent("您发送的是链接消息");
                respXML=MessageUtil.messageToXML(tm);
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
                tm.setContent("您发送的是地址消息");
                respXML=MessageUtil.messageToXML(tm);
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
                String eventType=requestMap.get("Event");
                if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                    tm.setContent("Boom! Boom! Boom!欢迎关注！看到这条消息的人三天内必有好事发生！");
                    respXML=MessageUtil.messageToXML(tm);
                }else if( eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXML; //再将tm对象转成xml格式
    }
}
