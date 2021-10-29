package com.qq.xgdemo.javaPush;

import android.content.Context;

import com.qq.xgdemo.util.LogUtil;
import com.qq.xgdemo.util.SharePreferenceUtil;

import org.json.JSONObject;

import java.util.ArrayList;

public class XingePush {

    public static JSONObject pushSingleDevice (Context context,String title, String token){
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            return V2Push.pushSingleDevice(context,title,token);
        }else{
            LogUtil.e("v3push2222222222");
            return V3Push.pushSingleDevice(context,title,token);
        }

    }


    public static void  pushDelayPush(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushDelayPush(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushDelayPush(context,fileName,token);
        }
    }

    public static void  pushAllPush(Context context,String fileName) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushAllPush(context,fileName);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushAllPush(context,fileName);
        }
    }

    public static JSONObject pushAllPushTimeArrive(Context context,String title,String fileName) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            //V2Push.pushAllPush(context,fileName);
            return null;
        }else{
            LogUtil.e("v3push2222222222");
            return V3Push.pushAllPushTimeArrive(context,title,fileName);
        }
    }

    public static void  pushAccountComboPush(Context context,String fileName,ArrayList<String> accounts) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushAccountComboPush(context,fileName,accounts);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushAccountComboPush(context,fileName,accounts);
        }

    }

    public static void  pushAccountPush(Context context,String fileName) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushAccountPush(context,fileName);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushAccountPush(context,fileName);
        }
    }

    public static void  pushTagPush(Context context,String fileName,ArrayList<String> tagList) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushTagPush(context,fileName,tagList);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushTagPush(context,fileName,tagList);
        }
    }

    public static void  pushMessageTransparent(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushMessageTransparent(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushMessageTransparent(context,fileName,token);
        }
    }

    public static void  pushNotificationIntentAction(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushNotificationIntentAction(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushNotificationIntentAction(context,fileName,token);
        }
    }

    public static void  pushNotificationIntent(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushNotificationIntent(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushNotificationIntent(context,fileName,token);
        }
    }

    public static void  pushNotificationActivity(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushNotificationActivity(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushNotificationActivity(context,fileName,token);
        }
    }

    public static void  pushNotificationUrl(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushNotificationUrl(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushNotificationUrl(context,fileName,token);
        }

    }

    public static void  pushNormalNotification(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushNormalNotification(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushNormalNotification(context,fileName,token);
        }
    }

    public static void  pushContentSpecificCombo(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushContentSpecificCombo(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushContentSpecificCombo(context,fileName,token);
        }
    }

    public static void  pushContentSpecificSymbol(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushContentSpecificSymbol(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushContentSpecificSymbol(context,fileName,token);
        }
    }

    public static void  pushContentSpeak(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushContentSpeak(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushContentSpeak(context,fileName,token);
        }
    }

    public static void  pushContentComplex(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushContentComplex(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushContentComplex(context,fileName,token);
        }
    }

    public static void  pushTitleSpecificCombo(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushTitleSpecificCombo(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushTitleSpecificCombo(context,fileName,token);
        }
    }

    public static void  pushTitleSpecificSymbol(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushTitleSpecificSymbol(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushTitleSpecificSymbol(context,fileName,token);
        }
    }

    public static void  pushTitleSpeak(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushTitleSpeak(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushTitleSpeak(context,fileName,token);
        }
    }

    public static void  pushTitleComplex(Context context,String fileName,String token) {
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushTitleComplex(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushTitleComplex(context,fileName,token);
        }
    }

    public static void pushNotifciationNOCustom(Context context,String fileName,String token){
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            V2Push.pushNotifciationNOCustom(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushNotifciationNOCustom(context,fileName,token);
        }
    }
    public static void pushNotifciationVibrator(Context context,String fileName,String token){
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            //V2Push.pushNotifciationNOCustom(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushSingleWithVibrate(context,fileName,token);
        }
    }
    public static void pushNotifciationRichMedia(Context context,String fileName,String token){
        if(SharePreferenceUtil.getPrefPushVersion(context)==2){
            LogUtil.e("v2push1111111111");
            //V2Push.pushNotifciationNOCustom(context,fileName,token);
        }else{
            LogUtil.e("v3push2222222222");
            V3Push.pushSingleWithRichMedia(context,fileName,token);
        }
    }

}
