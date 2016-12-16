package com.kyle.test;

import com.kyle.pojo.Token;
import com.kyle.util.CommonUtil;
import net.sf.json.JSONObject;

/**
 * Created by Java on 2016/10/25.
 */
public class TokenTest {
    public static void main(String[] args) throws Exception {
        Token token=CommonUtil.getAcessToken("wxc12fa433d2c83561","343c66d1b966fc80fb1b49c506670666");
        System.out.println(token.getAccess_token()+" "+token.getExpires_in());
    }
}


