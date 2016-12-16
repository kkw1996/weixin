package com.kyle.util;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.kyle.message_resp.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Java on 2016/10/9.
 */
public class MessageUtil {
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_TYPE_CLICK = "CLICK";

    public static HashMap<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        HashMap<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        recursiveParseXML(root,map);

        return map;
    }

    private static void recursiveParseXML(Element root, Map<String, String> map) {
        List<Element> elementList=root.elements();
        //判断有无子元素列表
        if(elementList.size()==0){

            map.put(root.getName(),root.getTextTrim());
        }else{
            for(Element e:elementList){
                recursiveParseXML(e,map);
            }
        }
    }

    private static XStream xStream=new XStream(new XppDriver(){  //重新自定义XStream类
        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){
                boolean cdata=true;
                public void startNode(String name,Class clazz){
                    super.startNode(name,clazz);
                }

                protected void writeText(QuickWriter writer, String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        }
    });

    public static String messageToXML(TextMessage textMessage){
        xStream.alias("xml",TextMessage.class);
        return xStream.toXML(textMessage);
    }
    public static String messageToXML(ImageMessage imageMessage){
        xStream.alias("xml",ImageMessage.class);
        return xStream.toXML(imageMessage);
    }
    public static String messageToXML(MusicMessage musicMessage){
        xStream.alias("xml",MusicMessage.class);
        return xStream.toXML(musicMessage);
    }
    public static String messageToXML(NewsMessage newsMessage){
        xStream.alias("xml",NewsMessage.class);
        xStream.alias("item", Article.class);
        return xStream.toXML(newsMessage);
    }
    public static String messageToXML(VoiceMessage voiceMessage){
        xStream.alias("xml",VoiceMessage.class);
        return xStream.toXML(voiceMessage);
    }
    public static String messageToXML(VideoMessage videoMessage){
        xStream.alias("xml",VideoMessage.class);
        return xStream.toXML(videoMessage);
    }
}
