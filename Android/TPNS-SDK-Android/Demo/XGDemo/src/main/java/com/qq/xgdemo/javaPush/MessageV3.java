package com.qq.xgdemo.javaPush;


import com.qq.xgdemo.util.CommonUtil;
import com.tencent.android.tpush.XGLocalMessage;
import com.tencent.xinge.bean.ActionType;
import com.tencent.xinge.bean.AtyAttr;
import com.tencent.xinge.bean.Browser;
import com.tencent.xinge.bean.ClickAction;
import com.tencent.xinge.bean.Message;
import com.tencent.xinge.bean.MessageAndroid;
import com.tencent.xinge.bean.TimeInterval;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MessageV3 {


    public static Message getMessage(String title, String content) {
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(0);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(null);

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;
    }
    public static Message getMessageVibrate(String title, String content) {
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(1);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(null);
        messageAndroid.setRing_raw("agtest");

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;
    }
    public static Message getMessageRichMedia(String title, String content) {
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(null);
        messageAndroid.setRing_raw("agtest");


        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);
        msg.setXgMediaResources("https://www.baidu.com/img/bd_logo1.png?qua=high");

        return msg;
    }
    public static Message getMessageAllPush(String title, String content) {
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(null);

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;
    }

    public static Message getMessageNoCustom(String title, String content) {
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(getNCustom());

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;

    }

    public static Message getMessageUrl(String title, String content) {
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_WEB.getType());
        Browser browser = new Browser();
        browser.setUrl("https://xg.qq.com");
        action.setBrowser(browser);

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(getNCustom());

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;


    }

    public static Message getMessageIntent(String title, String content){
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_INTENT.getType());
        action.setIntent("agtestscheme://com.agtest.hwpush/notify_detail");

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(getNCustom());

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;
    }

    public static Message getMessageIntentAction(String title, String content){

        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_ACTION.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);
        action.setIntent("com.qq.agtestFcmIntent");

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(getNCustom());

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;
    }

    public static Message getMessageActivity(String title,String content){
        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);
        action.setActivity("com.qq.xg4all.PressureTestActivity");

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(getNCustom());

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);


        return msg;

    }

    //透传消息，透传消息的熟悉在下发的时候设置，为保持个v2一致，才设置改方法
    public static Message getMessageTransparent(String title,String content){

        Message msg = new Message();
        ClickAction action = new ClickAction();

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(getMCustom());

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;
    }

    //延迟推送的通知，延迟推送设置在下发的时候设置，为保持跟v2一致，才设置该方法
    public static Message getMessageDelay(String title,String content){

        Message msg = new Message();

        ClickAction action = new ClickAction();
        action.setAction_type(ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType());
        AtyAttr atyAttr = new AtyAttr();
        action.setAty_attr(atyAttr);

        MessageAndroid messageAndroid = new MessageAndroid();
        messageAndroid.setAction(action);
        messageAndroid.setBuilder_id(3);
        messageAndroid.setRing(1);
        messageAndroid.setVibrate(0);
        messageAndroid.setClearable(1);
        messageAndroid.setLights(1);
        messageAndroid.setIcon_type(0);
        messageAndroid.setStyle_id(1);
        messageAndroid.setCustom_content(null);

        TimeInterval accepTime = new TimeInterval(0,0,23,59);
        ArrayList<TimeInterval> acceptTime = new ArrayList<>();
        acceptTime.add(accepTime);
        msg.setAccept_time(acceptTime);
        msg.setAndroid(messageAndroid);
        msg.setTitle(title);
        msg.setContent(content);

        return msg;
    }

    //本地通知 跳转打开url
    public static XGLocalMessage getLocalMessageUrl(String title, String content){
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
        localMessage.setHour(String.valueOf(CommonUtil.getLocalMsgHourMin()[0]));
        localMessage.setMin(String.valueOf(CommonUtil.getLocalMsgHourMin()[1]));
        localMessage.setAction_type(3);
        localMessage.setUrl("agtestscheme://com.agtest.hwpush/notify_detail");
        HashMap<String, Object> custom = new HashMap<String, Object>();
        custom.put("agtestNKey1", "agtestNValue1");
        custom.put("agtestNKey2", "agtestNValue2");
        localMessage.setCustomContent(custom);

        return localMessage;
    }

    //通知的custom
    public static String getNCustom(){
        JSONObject custom = new JSONObject();
        try {
            custom.put("agtestNKey1", "agtestNValue1");
            custom.put("agtestNKey2", "agtestNValue2");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  custom.toString();
    }
    //透传消息的custom，信鸽通知支持
    public static String getMCustom(){
        JSONObject custom = new JSONObject();
        try {
            custom.put("agtestMKey1", "agtestMValue1");
            custom.put("agtestMKey2", "agtestMValue2");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  custom.toString();
    }
}
