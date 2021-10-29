package com.qq.xgdemo.javaPush;

import com.qq.xgdemo.cloud.ClickAction;
import com.qq.xgdemo.cloud.Message;
import com.qq.xgdemo.cloud.Style;
import com.qq.xgdemo.cloud.TimeInterval;
import com.qq.xgdemo.util.CommonUtil;
import com.tencent.android.tpush.XGLocalMessage;

import java.util.HashMap;
import java.util.Map;

public class MessageV2 {


    public static Message getMessage(String title, String content) {
        Message msg = new Message();
        msg.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        Map<String, Object> custom = new HashMap<String, Object>();
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;
    }
    public static Message getMessageNoCustom(String title, String content) {
        Message msg = new Message();
        msg.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;
    }

    public static Message getMessageUrl(String title, String content) {
        Message msg = new Message();
        msg.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        action.setActionType(ClickAction.TYPE_URL);
        action.setUrl("http://xg.qq.com");
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;
    }

    public static Message getMessageIntent(String title, String content){
        Message msg = new Message();
        msg.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        action.setActionType(ClickAction.TYPE_INTENT);
        action.setIntent("agtestscheme://com.agtest.hwpush/notify_detail");
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;
    }

    public static Message getMessageIntentAction(String title, String content){
        Message msg = new Message();
        msg.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        action.setActionType(ClickAction.TYPE_INTENT_WITH_ACTION);
        action.setIntent("com.qq.agtestFcmIntent");
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;
    }

    public static Message getMessageActivity(String title,String content){

        Message msg = new Message();
        msg.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        action.setActionType(ClickAction.TYPE_ACTIVITY);
        action.setActivity("com.qq.xgdemo.PressureTestActivity");
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;
    }

    //透传消息
    public static Message getMessageTransparent(String title,String content){

        Message msg = new Message();
        msg.setType(Message.TYPE_MESSAGE);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        //action.setActionType(ClickAction.TYPE_ACTIVITY);
        //action.setActivity("com.qq.xgdemo.PressureTestActivity");
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;

    }

    //延迟推送的通知
    public static Message getMessageDelay(String title,String content){
        Message msg = new Message();
        msg.setType(Message.TYPE_NOTIFICATION);
        Style style = new Style(2, 1, 0, 1, 0);
        msg.setStyle(style);
        ClickAction action = new ClickAction();
        // action.setActionType(ClickAction.TYPE_URL);
        //action.setUrl("http://xg.qq.com");
        Map<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        msg.setTitle(title);
        msg.setContent(content);
        msg.setAction(action);
        msg.setCustom(custom);
        msg.setSendTime(CommonUtil.getDelayPushTime());
        msg.setExpireTime(86400);
        TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        msg.addAcceptTime(acceptTime1);

        return msg;
    }
    //本地通知 跳转打开url
    public static XGLocalMessage getLocalMessageUrl(String title,String content){
        XGLocalMessage localMessage = new XGLocalMessage();
        //设置通知类型：1 通知，2 透传消息
        localMessage.setType(1);
        localMessage.setTitle(title);
        localMessage.setContent(content);
        localMessage.setDate(CommonUtil.getLocalMsgTime());
        localMessage.setHour(String.valueOf(CommonUtil.getLocalMsgHourMin()[0]));
        localMessage.setMin(String.valueOf(CommonUtil.getLocalMsgHourMin()[1]));
        localMessage.setAction_type(2);
        localMessage.setUrl("http://xg.qq.com");
        HashMap<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        localMessage.setCustomContent(custom);

        return localMessage;
    }
    //本地通知 跳转打开Intent
    public static XGLocalMessage getLocalMessageIntent(String title,String content){
        XGLocalMessage localMessage = new XGLocalMessage();
        //设置通知类型：1 通知，2 透传消息
        localMessage.setType(1);
        localMessage.setTitle(title);
        localMessage.setContent(content);
        localMessage.setDate(CommonUtil.getLocalMsgTime());
        int [] time = CommonUtil.getLocalMsgHourMin();
        localMessage.setHour(String.valueOf(time[0]));
        localMessage.setMin(String.valueOf(time[1]));
        localMessage.setAction_type(3);
        localMessage.setUrl("agtestscheme://com.agtest.hwpush/notify_detail");
        HashMap<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        localMessage.setCustomContent(custom);

        return localMessage;
    }




}
