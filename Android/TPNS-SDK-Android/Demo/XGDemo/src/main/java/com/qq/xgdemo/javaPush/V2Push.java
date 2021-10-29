package com.qq.xgdemo.javaPush;

import android.content.Context;

import com.qq.xgdemo.cloud.XingeApp;
import com.qq.xgdemo.util.CommonUtil;
import com.qq.xgdemo.util.Constants;
import com.qq.xgdemo.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class V2Push {

    public static JSONObject pushSingleDevice (Context context,String title,String token){
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);


        JSONObject ret = null;
        try {
            ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;

    }

    public static void  pushDelayPush(Context context, String fileName, String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessageDelay(title, content));
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

    public static void  pushAllPush(Context context,String fileName) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);
        try {
            JSONObject ret = xingeApp.pushAllDevice(context, 0, MessageV2.getMessage(title, content));
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

    public static void  pushAccountComboPush(Context context,String fileName,ArrayList<String> accounts) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);
        try {
            JSONObject ret = xingeApp.pushAccountList(context, 0, accounts, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "账号列表通知推送成功，通知标题为： " + title);
                LogUtil.d("账号列表推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "账号列表通知推送失败，通知标题为： " + title);
                LogUtil.d("账号列表通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushAccountPush(Context context,String fileName) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);
        try {
            JSONObject ret = xingeApp.pushSingleAccount(context, 0, Constants.XG_TEST_ACCOUNT, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "账号绑定通知推送成功，通知标题为： " + title);
                LogUtil.d("账号绑定推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "账号绑定通知推送失败，通知标题为： " + title);
                LogUtil.d("账号绑定通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushTagPush(Context context,String fileName,ArrayList<String> tagList) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);
        try {
            JSONObject ret = xingeApp.pushTags(context, 0, tagList, "OR", MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "TAG通知推送成功，通知标题为： " + title);
                LogUtil.d("TAG通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "TAG通知推送失败，通知标题为： " + title);
                LogUtil.d("TAG通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushMessageTransparent(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessageTransparent(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "透传消息推送成功，通知标题为： " + title);
                LogUtil.d("透传消息推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "透传消息推送失败，通知标题为： " + title);
                LogUtil.d("透传消息推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushNotificationIntentAction(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessageIntentAction(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "跳转Intent_action通知推送成功，通知标题为： " + title);
                LogUtil.d("跳转Intent_action通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "跳转Intent_action通知推送失败，通知标题为： " + title);
                LogUtil.d("跳转Intent_action通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushNotificationIntent(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessageIntent(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "跳转Intent通知推送成功，通知标题为： " + title);
                LogUtil.d("跳转Intent通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "跳转Intent通知推送失败，通知标题为： " + title);
                LogUtil.d("跳转Intent通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushNotificationActivity(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessageActivity(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "跳转activity通知推送成功，通知标题为： " + title);
                LogUtil.d("跳转activity通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "跳转activity通知推送失败，通知标题为： " + title);
                LogUtil.d("跳转activity通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushNotificationUrl(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessageUrl(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "跳转URL通知推送成功，通知标题为： " + title);
                LogUtil.d("跳转URL通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "跳转URL通知推送失败，通知标题为： " + title);
                LogUtil.d("跳转URL通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushNormalNotification(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "正常的通知推送成功，通知标题为： " + title);
                LogUtil.d("正常的通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "正常的通知推送失败，通知标题为： " + title);
                LogUtil.d("正常的通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushContentSpecificCombo(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getSpecificCombo(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "内容为特殊字符组合的通知推送成功，通知内容为： " + content);
                LogUtil.d("内容为特殊字符组合的通知推送成功，通知内容为： " + content);
            } else {
                CommonUtil.writeResultToLog(fileName, "内容为特殊字符组合的通知推送失败，通知内容为： " + content);
                LogUtil.d("内容为特殊字符组合的通知推送失败，通知内容为： " + content);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushContentSpecificSymbol(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getSpecificSymbol(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "内容为特殊字符的通知推送成功，通知内容为： " + content);
                LogUtil.d("内容为特殊字符的通知推送成功，通知内容为： " + content);
            } else {
                CommonUtil.writeResultToLog(fileName, "内容为特殊字符的通知推送失败，通知内容为： " + content);
                LogUtil.d("内容为特殊字符的通知推送失败，通知内容为： " + content);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushContentSpeak(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getSpeak(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "内容为火星文的通知推送成功，通知内容为： " + content);
                LogUtil.d("内容为火星文的通知推送成功，通知内容为： " + content);
            } else {
                CommonUtil.writeResultToLog(fileName, "内容为火星文的通知推送失败，通知内容为： " + content);
                LogUtil.d("内容为火星文的通知推送失败，通知内容为： " + content);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushContentComplex(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomString(10);
        String content = CommonUtil.getComplex(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "内容为繁体字的通知推送成功，通知内容为： " + content);
                LogUtil.d("内容为特殊字符的通知推送成功，通知内容为： " + content);
            } else {
                CommonUtil.writeResultToLog(fileName, "内容为繁体字的通知推送失败，通知内容为： " + content);
                LogUtil.d("内容为繁体字的通知推送失败，通知内容为： " + content);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushTitleSpecificCombo(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getSpecificCombo(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "标题为特殊字符组合的通知推送成功，通知标题为： " + title);
                LogUtil.d("标题为特殊字符组合的通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "标题为特殊字符组合的通知推送失败，通知标题为： " + title);
                LogUtil.d("标题为特殊字符组合的通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushTitleSpecificSymbol(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getSpecificSymbol(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "标题为特殊字符的通知推送成功，通知标题为： " + title);
                LogUtil.d("标题为特殊字符的通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "标题为特殊字符的通知推送失败，通知标题为： " + title);
                LogUtil.d("标题为特殊字符的通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushTitleSpeak(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getSpeak(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "标题为火星文的通知推送成功，通知标题为： " + title);
                LogUtil.d("标题为火星文的通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "标题为火星文的通知推送失败，通知标题为： " + title);
                LogUtil.d("标题为火星文的通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void  pushTitleComplex(Context context,String fileName,String token) {
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getComplex(10);
        String content = CommonUtil.getRandomString(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessage(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "标题为繁体的通知推送成功，通知标题为： " + title);
                LogUtil.d("标题为繁体的通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "标题为繁体的通知推送失败，通知标题为： " + title);
                LogUtil.d("标题为繁体的通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public static void pushNotifciationNOCustom(Context context,String fileName,String token){
        XingeApp xingeApp = (XingeApp)PushFactory.getXingeApp(context);
        String title = CommonUtil.getRandomStringChinese(10);
        String content = CommonUtil.getRandomStringChinese(20);
        LogUtil.d("title : " + title);
        LogUtil.d("content : " + content);

        try {
            JSONObject ret = xingeApp.pushSingleDevice(context, token, MessageV2.getMessageNoCustom(title, content));
            LogUtil.d("返回的结果ret: " + ret.toString());
            if (ret.getInt("ret_code") == 0) {
                CommonUtil.writeResultToLog(fileName, "没custom的通知推送成功，通知标题为： " + title);
                LogUtil.d("没custom的通知推送成功，通知标题为： " + title);
            } else {
                CommonUtil.writeResultToLog(fileName, "没custom的通知推送失败，通知标题为： " + title);
                LogUtil.d("没custom的通知推送失败，通知标题为： " + title);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
