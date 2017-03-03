package com.kyle.test;

import com.kyle.pojo.Token;
import com.kyle.util.CommonUtil;
import net.sf.json.JSONObject;

/**
 * Created by Java on 2016/10/25.
 */
public class TokenTest {
    public static void main(String[] args) throws Exception {
        Token token=CommonUtil.getAcessToken("wxc12fa433d2c83561","d5f78610a074fb55994c9ea22daac923");
        System.out.println(token.getAccess_token()+" "+token.getExpires_in());
    }
}


