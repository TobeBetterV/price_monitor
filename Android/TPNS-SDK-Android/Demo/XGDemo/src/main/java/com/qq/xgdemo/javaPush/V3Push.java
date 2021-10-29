package com.qq.xgdemo.javaPush;

import android.content.Context;

import com.qq.xgdemo.util.CommonUtil;
import com.qq.xgdemo.util.Constants;
import com.qq.xgdemo.util.LogUtil;
import com.tencent.xinge.XingeApp;
import com.tencent.xinge.bean.*;
import com.tencent.xinge.push.app.PushAppRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class V3Push {

    public static JSONObject pushSingleDevice (Context context,String title,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
        return ret;
    }

    public static void  pushDelayPush(Context context, String fileName, String token) {

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setSend_time(CommonUtil.getDelayPushTime());
        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageDelay(title,content));
        ArrayList<String> tokenList = new ArrayList<>();
        tokenList.add(token);
        pushAppRequest.setToken_list(tokenList);

        try {
            JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
            LogUtil.d("url: "+pushAppRequest.toString());
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "延迟5分钟通知推送成功，通知标题为： " + title);
                LogUtil.d("延迟5分钟通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "延迟5分钟通知推送失败，通知标题为： " + title);
                LogUtil.d("延迟5分钟通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    public static void pushAllPush(Context context,String fileName){

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.all);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageAllPush(title,content));

        try {
            LogUtil.d("url: "+pushAppRequest.toString());
            JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "全推推送成功，通知标题为： " + title);
                LogUtil.d("全推推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "全推推送失败，通知标题为： " + title);
                LogUtil.d("全推推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
    public static JSONObject pushAllPushTimeArrive(Context context,String title,String fileName){

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.all);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageAllPush(title,content));

        try {
            LogUtil.d("url: "+pushAppRequest.toString());
            JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
            LogUtil.d("url: "+pushAppRequest.toString());
            LogUtil.d(ret.toString());
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                //CommonUtil.writeResultToLog(fileName, "全推推送成功，通知标题为： " + title);
                LogUtil.d("全推推送成功，通知标题为： " + title);
            } else {
                //CommonUtil.writeResultToLog(fileName, "全推推送失败，通知标题为： " + title);
                LogUtil.d("全推推送失败，通知标题为： " + title);
            }
            return ret;

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return null;

    }

    public static void  pushAccountComboPush(Context context,String fileName,ArrayList<String> accounts) {

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.account_list);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));
        pushAppRequest.setAccount_list(accounts);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());


    }

    public static void  pushAccountPush(Context context,String fileName) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.account);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));
        ArrayList<String> account = new ArrayList<>();
        account.add(Constants.XG_TEST_ACCOUNT);
        pushAppRequest.setAccount_list(account);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushTagPush(Context context,String fileName,ArrayList<String> tagList) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.tag);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        TagListObject tagListObject = new TagListObject();
        tagListObject.setTags(tagList);
        tagListObject.setOp(OpType.OR);

        pushAppRequest.setTag_list(tagListObject);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushMessageTransparent(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.message);
        pushAppRequest.setMessage(MessageV3.getMessageTransparent(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushNotificationIntentAction(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageIntentAction(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushNotificationIntent(Context context,String fileName,String token) {

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageIntent(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());

    }

    public static void  pushNotificationActivity(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageActivity(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }


    public static void  pushNotificationUrl(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageUrl(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);
        LogUtil.d("url::"+pushAppRequest.toString());

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushNormalNotification(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushContentSpecificCombo(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getSpecificCombo(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushContentSpecificSymbol(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getSpecificCombo(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushContentSpeak(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getSpeak(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushContentComplex(Context context,String fileName,String token) {

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getComplex(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void  pushTitleSpecificCombo(Context context,String fileName,String token) {

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getSpecificCombo(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }
    public static void  pushTitleSpecificSymbol(Context context,String fileName,String token) {

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getSpecificSymbol(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }
    public static void  pushTitleSpeak(Context context,String fileName,String token) {

        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getSpeak(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }




    public static void  pushTitleComplex(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getComplex(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));
        ArrayList<String> tokenList = new ArrayList<>();
        tokenList.add(token);
        pushAppRequest.setToken_list(tokenList);

        LogUtil.d(pushAppRequest.toString());

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static void pushNotifciationNOCustom(Context context,String fileName,String token){

        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomStringChinese(10);
        String content = CommonUtil.getRandomStringChinese(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setSend_time(CommonUtil.getDelayPushTime());
        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessage(title,content));
        ArrayList<String> tokenList = new ArrayList<>();
        tokenList.add(token);
        pushAppRequest.setToken_list(tokenList);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
    }

    public static JSONObject pushSingleWithVibrate (Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomStringChinese(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageVibrate(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
        return ret;
    }

    public static JSONObject pushSingleWithRichMedia (Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp) PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomStringChinese(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        PushAppRequest pushAppRequest = new PushAppRequest();

        pushAppRequest.setExpire_time(86400);
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);
        pushAppRequest.setMessage(MessageV3.getMessageRichMedia(title,content));

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        pushAppRequest.setToken_list(tokens);

        JSONObject ret = xingeApp.pushApp(pushAppRequest.toString());
        LogUtil.d("url: "+pushAppRequest.toString());
        LogUtil.d(ret.toString());
        return ret;
    }



}