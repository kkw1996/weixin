package com.kyle.util;

import net.sf.json.JSONObject;

/**
 * Created by Java on 2016/11/3.
 */
public class MenuUtil {
    private final static String MENU_CREATE_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public  static boolean createMenu(String json,String accessToken) throws Exception {
        boolean result=false;
        String requestUrl=MENU_CREATE_URL.replace("ACCESS_TOKEN",accessToken);
        String respJson= CommonUtil.httpsRequest(requestUrl,"POST",json);
        JSONObject jsonObject=JSONObject.fromObject(respJson);
        if(null!=jsonObject){
            int errCode=jsonObject.getInt("errcode");
            String errMsg=jsonObject.getString("errmsg");
            if(0==errCode){
                result=true;
            }else {
                result=false;
                System.out.println("菜单创建失败"+jsonObject);
            }
        }
        return  result;
    }
}
