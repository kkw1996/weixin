package com.kyle.util;

import com.kyle.pojo.Face;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Java on 2016/11/14.
 */
public class FacePlusPlusUtil {
    public static String detectFace(String imageUrl) throws Exception {
        String requestUrl="http://apicn.faceplusplus.com/v2/detection/detect?api_key=YOUR_API_KEY&api_secret=YOUR_API_SECRET&url=URL&attribute=glass,pose,gender,age,race,smiling";
        requestUrl=requestUrl.replace("YOUR_API_KEY","931febc801b708eef080ecd4b001231d");
        requestUrl=requestUrl.replace("YOUR_API_SECRET","x3uWpLQ4KWOjjyRWQjrZTAYU-2sF4c1B");
        requestUrl=requestUrl.replace("URL",CommonUtilHttp.urlEncodeUTF8(imageUrl));
        //发起请求

        String respJSON=CommonUtilHttp.httpRequest(requestUrl,"GET",null);

        //解析
        JSONArray jsonArray= JSONObject.fromObject(respJSON).getJSONArray("face");
        if(jsonArray.size()==0){
            return null;
        }
        List<Face> faceList=new ArrayList<>();

        for(int i=0;i<jsonArray.size();i++){
            JSONObject faceObject= (JSONObject) jsonArray.get(i);
            JSONObject attrObject=faceObject.getJSONObject("attribute");
            JSONObject posObject=faceObject.getJSONObject("position");

            Face face=new Face();
            face.setFaceid(faceObject.getString("face_id"));
            face.setAgeValue(attrObject.getJSONObject("age").getInt("value"));
            face.setAgeRange(attrObject.getJSONObject("age").getInt("range"));
            face.setGenderValue(genderConvert(attrObject.getJSONObject("gender").getString("value")));
            face.setGenderConfident(attrObject.getJSONObject("gender").getDouble("confidence"));
            face.setRaceValue(raceConvert(attrObject.getJSONObject("race").getString("value")));
            face.setRaceConfident(attrObject.getJSONObject("race").getDouble("confidence"));
            face.setSmilingValue(attrObject.getJSONObject("smiling").getDouble("value"));
            face.setCenterX(posObject.getJSONObject("center").getDouble("x"));
            face.setCenterY(posObject.getJSONObject("center").getDouble("y"));
            faceList.add(face);
        }
        Collections.sort(faceList);

        StringBuffer buffer=new StringBuffer();
        if(1==faceList.size()){
            buffer.append("共检测到").append(faceList.size()).append("张人脸").append("\n");
            for(Face face:faceList){
                buffer.append(face.getRaceValue() ).append("人种，");
                buffer.append(face.getGenderValue()).append(",");
                buffer.append(face.getAgeValue()).append("岁左右");
            }
        }else if(faceList.size()>1){
            buffer.append("共检测到").append(faceList.size()).append("张人脸，从左至右依次为：").append("\n");
            for(Face face:faceList){
                buffer.append(face.getRaceValue() ).append("人种，");
                buffer.append(face.getGenderValue()).append("，");
                buffer.append(face.getAgeValue()).append("岁左右").append("\n");
            }
        }

        return buffer.toString();
    }

    public static String genderConvert(String gender){
        String result="男性";
        if(gender.equals("Female")){
            result="女性";
        }
        return result;
    }

    public static String raceConvert(String race){
        String result="黄";
        if(race.equals("White")){
            result="白";
        }else if(race.equals("Black")){
            result="黑";
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String result=detectFace("http://hiphotos.baidu.com/imgad/pic/item/f603918fa0ec08fad6b2871b51ee3d6d54fbdad8.jpg");
        System.out.println(result);
    }
}
