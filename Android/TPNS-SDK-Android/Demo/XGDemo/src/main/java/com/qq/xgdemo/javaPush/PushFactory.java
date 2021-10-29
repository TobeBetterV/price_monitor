package com.qq.xgdemo.javaPush;


import android.content.Context;

import com.qq.xgdemo.cloud.XingeApp;
import com.qq.xgdemo.util.Constants;
import com.qq.xgdemo.util.SharePreferenceUtil;

public class PushFactory {

    private static XingeApp v2XingePush = null;
    private static com.tencent.xinge.XingeApp v3XingePush = null;


    //用户保证全局唯一的推送XingeApp
    public static synchronized Object getXingeApp(Context context) {

        if (SharePreferenceUtil.getPrefPushVersion(context) == 2) {
            if (v2XingePush == null) {
                v2XingePush = new XingeApp(Constants.XG_ACCESS_ID, Constants.XG_SECRET_KEY);
            }
            return v2XingePush;

        }else {
            if (v3XingePush == null) {
                v3XingePush = new com.tencent.xinge.XingeApp.Builder()
                        //.appId(Constants.XG_APP_ID)
                        .appId(String.valueOf(Constants.XG_ACCESS_ID))
                        .secretKey(Constants.XG_SECRET_KEY)
                        //.domainUrl("https://openapi.xg.qq.com/2100271539/")
                        //.domainUrl("https://openapi.xg.qq.com/")
//                        .domainUrl("https://pushapi.xg.qq.com/")
//                        .domainUrl("https://test.api.tpns.tencent.com/")
                        .domainUrl("https://api.tpns.tencent.com/")
                        //.domainUrl("http://test.openapi.xg.qq.com/")
                        //.domainUrl("https://testopenapi.xg.qq.com/")
                        .build();

            }
            return v3XingePush;

        }

    }

}
