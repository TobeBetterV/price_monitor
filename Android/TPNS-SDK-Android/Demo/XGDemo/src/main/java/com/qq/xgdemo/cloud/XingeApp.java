package com.qq.xgdemo.cloud;

import android.annotation.SuppressLint;
import android.content.Context;

import com.qq.xgdemo.util.LogUtil;
import com.qq.xgdemo.util.SharePreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XingeApp {

    public static final String RESTAPI_PUSHSINGLEDEVICE = "http://openapi.xg.qq.com/v2/push/single_device";
    public static final String RESTAPI_PUSHSINGLEDEVICE_TEST = "http://testopenapi.xg.qq.com/v2/push/single_device";


    public static final String RESTAPI_PUSHSINGLEACCOUNT = "http://openapi.xg.qq.com/v2/push/single_account";
    public static final String RESTAPI_PUSHSINGLEACCOUNT_TEST = "http://testopenapi.xg.qq.com/v2/push/single_account";

    public static final String RESTAPI_PUSHTAGS = "http://openapi.xg.qq.com/v2/push/tags_device";
    public static final String RESTAPI_PUSHTAGS_TEST = "http://testopenapi.xg.qq.com/v2/push/tags_device";

    public static final String RESTAPI_PUSHALLDEVICE = "http://openapi.xg.qq.com/v2/push/all_device";
    public static final String RESTAPI_PUSHALLDEVICE_TEST = "http://testopenapi.xg.qq.com/v2/push/all_device";

    public static final String RESTAPI_PUSHACCOUNTLIST = "http://openapi.xg.qq.com/v2/push/account_list";
    public static final String RESTAPI_PUSHACCOUNTLIST_TEST = "http://testopenapi.xg.qq.com/v2/push/account_list";

    public static final String RESTAPI_QUERYPUSHSTATUS = "http://openapi.xg.qq.com/v2/push/get_msg_status";
    public static final String RESTAPI_QUERYDEVICECOUNT = "http://openapi.xg.qq.com/v2/application/get_app_device_num";
    public static final String RESTAPI_QUERYTAGS = "http://openapi.xg.qq.com/v2/tags/query_app_tags";
    public static final String RESTAPI_CANCELTIMINGPUSH = "http://openapi.xg.qq.com/v2/push/cancel_timing_task";
    public static final String RESTAPI_BATCHSETTAG = "http://openapi.xg.qq.com/v2/tags/batch_set";
    public static final String RESTAPI_BATCHDELTAG = "http://openapi.xg.qq.com/v2/tags/batch_del";
    public static final String RESTAPI_QUERYTOKENTAGS = "http://openapi.xg.qq.com/v2/tags/query_token_tags";
    public static final String RESTAPI_QUERYTAGTOKENNUM = "http://openapi.xg.qq.com/v2/tags/query_tag_token_num";
    public static final String RESTAPI_CREATEMULTIPUSH = "http://openapi.xg.qq.com/v2/push/create_multipush";
    public static final String RESTAPI_PUSHACCOUNTLISTMULTIPLE = "http://openapi.xg.qq.com/v2/push/account_list_multiple";
    public static final String RESTAPI_PUSHDEVICELISTMULTIPLE = "http://openapi.xg.qq.com/v2/push/device_list_multiple";
    public static final String RESTAPI_QUERYINFOOFTOKEN = "http://openapi.xg.qq.com/v2/application/get_app_token_info";
    public static final String RESTAPI_QUERYTOKENSOFACCOUNT = "http://openapi.xg.qq.com/v2/application/get_app_account_tokens";
    public static final String RESTAPI_DELETETOKENOFACCOUNT = "http://openapi.xg.qq.com/v2/application/del_app_account_tokens";
    public static final String RESTAPI_DELETEALLTOKENSOFACCOUNT = "http://openapi.xg.qq.com/v2/application/del_app_account_all_tokens";

    public static final String HTTP_POST = "POST";
    public static final String HTTP_GET = "GET";

    public static final int DEVICE_ALL = 0;
    public static final int DEVICE_BROWSER = 1;
    public static final int DEVICE_PC = 2;
    public static final int DEVICE_ANDROID = 3;
    public static final int DEVICE_IOS = 4;
    public static final int DEVICE_WINPHONE = 5;

    public static final int IOSENV_PROD = 1;
    public static final int IOSENV_DEV = 2;

    public static final long IOS_MIN_ID = 2200000000L;


    public XingeApp(long accessID, String secretKey) {
        this.m_accessId = accessID;
        this.m_secretKey = secretKey;
    }


    protected String generateSign(String method, String url, Map<String, Object> params) {
        List<Map.Entry<String, Object>> paramList = new ArrayList<Map.Entry<String, Object>>(params.entrySet());
        Collections.sort(paramList, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        String paramStr = "";
        String md5Str = "";
        for (Map.Entry<String, Object> entry : paramList) {
            paramStr += entry.getKey() + "=" + entry.getValue().toString();
        }
        try {
            URL u = new URL(url);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String s = method + u.getHost() + u.getPath() + paramStr + this.m_secretKey;
            System.out.println(URLDecoder.decode(s, "UTF-8"));
            byte[] bArr = s.getBytes("UTF-8");
            byte[] md5Value = md5.digest(bArr);
            BigInteger bigInt = new BigInteger(1, md5Value);
            md5Str = bigInt.toString(16);
            while (md5Str.length() < 32) {
                md5Str = "0" + md5Str;
            }
            //System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return md5Str;
    }

    @SuppressLint("NewApi")
    protected JSONObject callRestful(String url, Map<String, Object> params) throws JSONException {
        String temp;
        String ret = "";
        JSONObject jsonRet = null;
        String sign = generateSign("POST", url, params);
        if (sign.isEmpty())
            return new JSONObject("{\"ret_code\":-1,\"err_msg\":\"generateSign error\"}");
        params.put("sign", sign);
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(3000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            StringBuffer param = new StringBuffer();
            for (String key : params.keySet()) {
                param.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "UTF-8")).append("&");
            }
            conn.getOutputStream().write(param.toString().getBytes("UTF-8"));
            System.out.println(params);
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((temp = br.readLine()) != null) {
                ret += temp;
            }
            br.close();
            isr.close();
            conn.disconnect();
            //System.out.println(ret);
            jsonRet = new JSONObject(ret);

        } catch (java.net.SocketTimeoutException e) {
            //e.printStackTrace();
            jsonRet = new JSONObject("{\"ret_code\":-1,\"err_msg\":\"call restful timeout\"}");
        } catch (Exception e) {
            //e.printStackTrace();
            jsonRet = new JSONObject("{\"ret_code\":-1,\"err_msg\":\"call restful error\"}");
        }
        return jsonRet;
    }

    protected boolean ValidateToken(String token) {
        if (this.m_accessId >= 2200000000L) {
            return token.length() == 64;
        } else {
            return (token.length() == 40 || token.length() == 64);
        }
    }

    protected Map<String, Object> InitParams() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("access_id", this.m_accessId);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return params;
    }

    protected boolean ValidateMessageType(Message message) {
        if (this.m_accessId < IOS_MIN_ID)
            return true;
        else
            return false;
    }

    protected boolean ValidateMessageType(MessageIOS message, int environment) {
        if (this.m_accessId >= IOS_MIN_ID && (environment == IOSENV_PROD || environment == IOSENV_DEV))
            return true;
        else
            return false;
    }

    private long m_accessId;
    private String m_secretKey;

    // ======================= 简易api接口 v1.1.4引入 =======================

    /**
     * 推送给指定的设备，限Android系统使用
     *
     * @param accessId 推送目标应用ID
     * @param secretKey 推送密钥
     * @param title 标题 标题
     * @param content 内容
     * @param token 目标设备token
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
//    public static JSONObject pushTokenAndroid(long accessId, String secretKey, String title, String content, String token) throws JSONException {
//        Message message = new Message();
//        message.setType(Message.TYPE_NOTIFICATION);
//        message.setTitle(title);
//        message.setContent(content);
//
//        XingeApp xinge = new XingeApp(accessId, secretKey);
//        JSONObject ret = xinge.pushSingleDevice(token, message);
//		
//        return (ret);
//    }

    /**
     * 推送给指定的账号，限Android系统使用
     *
     * @param accessId 推送目标应用ID
     * @param secretKey 推送密钥
     * @param title 标题
     * @param content 内容
     * @param account 目标账号
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    //   public static JSONObject pushAccountAndroid(long accessId, String secretKey, String title, String content, String account) throws JSONException {
    //      Message message = new Message();
    //      message.setType(Message.TYPE_NOTIFICATION);
    //      message.setTitle(title);
    //      message.setContent(content);

    //       XingeApp xinge = new XingeApp(accessId, secretKey);
    //      JSONObject ret = xinge.pushSingleAccount(0, account, message);
    //      return (ret);
    //  }

    /**
     * 推送给所有注册设备，限Android系统使用
     *
     * @param accessId  推送目标应用ID
     * @param secretKey 推送密钥
     * @param title     标题
     * @param content   内容
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
  //  public static JSONObject pushAllAndroid(long accessId, String secretKey, String title, String content) throws JSONException {
 //       Message message = new Message();
 //       message.setType(Message.TYPE_NOTIFICATION);
 //       message.setTitle(title);
 //       message.setContent(content);
//
 //       XingeApp xinge = new XingeApp(accessId, secretKey);
//        JSONObject ret = xinge.pushAllDevice(0, message);
 //       return (ret);
 //   }

    /**
     * 推送给带有指定tag的设备，限Android设备使用
     *
     * @param accessId  推送目标应用ID
     * @param secretKey 推送密钥
     * @param title     标题
     * @param content   内容
     * @param tag       指定的标签
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public static JSONObject pushTagAndroid(Context context, long accessId, String secretKey, String title, String content, String tag) throws JSONException {
        Message message = new Message();
        message.setType(Message.TYPE_NOTIFICATION);
        message.setTitle(title);
        message.setContent(content);

        XingeApp xinge = new XingeApp(accessId, secretKey);
        List<String> tagList = new ArrayList<String>();
        tagList.add(tag);
        JSONObject ret = xinge.pushTags(context, 0, tagList, "OR", message);
        return (ret);
    }

    /**
     * 推送给指定设备，限iOS系统使用
     *
     * @param accessId  推送目标应用ID
     * @param secretKey 推送密钥
     * @param content   内容
     * @param token     目标设备token
     * @param env       推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public static JSONObject pushTokenIos(long accessId, String secretKey, String content, String token, int env) throws JSONException {
        MessageIOS message = new MessageIOS();
        message.setAlert(content);
        message.setBadge(1);
        message.setSound("beep.wav");

        XingeApp xinge = new XingeApp(accessId, secretKey);
        JSONObject ret = xinge.pushSingleDevice(token, message, env);
        return (ret);
    }

    /**
     * 推送给指定账号，限iOS系统使用
     *
     * @param accessId  推送目标应用ID
     * @param secretKey 推送密钥
     * @param content   内容
     * @param account   目标账号
     * @param env       推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public static JSONObject pushAccountIos(long accessId, String secretKey, String content, String account, int env) throws JSONException {
        MessageIOS message = new MessageIOS();
        message.setAlert(content);
        message.setBadge(1);
        message.setSound("beep.wav");

        XingeApp xinge = new XingeApp(accessId, secretKey);
        JSONObject ret = xinge.pushSingleAccount(0, account, message, env);
        return (ret);
    }

    /**
     * 推送给全部注册设备，限iOS系统使用
     *
     * @param accessId  推送目标应用ID
     * @param secretKey 推送密钥
     * @param content   内容
     * @param env       推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public static JSONObject pushAllIos(long accessId, String secretKey, String content, int env) throws JSONException {
        MessageIOS message = new MessageIOS();
        message.setAlert(content);
        message.setBadge(1);
        message.setSound("beep.wav");

        XingeApp xinge = new XingeApp(accessId, secretKey);
        JSONObject ret = xinge.pushAllDevice(0, message, env);
        return (ret);
    }

    /**
     * 推送给带有指定tag的设备，限iOS系统使用
     *
     * @param accessId  推送目标应用ID
     * @param secretKey 推送密钥
     * @param content   内容
     * @param tag       指定的标签
     * @param env       推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public static JSONObject pushTagIos(long accessId, String secretKey, String content, String tag, int env) throws JSONException {
        MessageIOS message = new MessageIOS();
        message.setAlert(content);
        message.setBadge(1);
        message.setSound("beep.wav");

        XingeApp xinge = new XingeApp(accessId, secretKey);
        List<String> tagList = new ArrayList<String>();
        tagList.add(tag);
        JSONObject ret = xinge.pushTags(0, tagList, "OR", message, env);
        return (ret);
    }


    // =================== 详细的api接口 =====================

    /**
     * 推送给指定设备，限Android系统使用
     *
     * @param deviceToken 目标设备token
     * @param message     待推送的消息
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushSingleDevice(Context context, String deviceToken, Message message) throws JSONException {
        if (!ValidateMessageType(message)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("multi_pkg", message.getMultiPkg());
        params.put("device_token", deviceToken);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        if (SharePreferenceUtil.isTestEnv(context)) {
            LogUtil.d("调用测试环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHSINGLEDEVICE_TEST, params);
        } else {
            LogUtil.d("调用现网环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHSINGLEDEVICE, params);
        }


    }

    /**
     * 推送给指定设备，限iOS系统使用
     *
     * @param deviceToken 目标设备token
     * @param message     待推送的消息
     * @param environment 推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     */
    public JSONObject pushSingleDevice(String deviceToken, MessageIOS message, int environment) throws JSONException {
        if (!ValidateMessageType(message, environment)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type or environment error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("device_token", deviceToken);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("environment", environment);

        if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0) {
            params.put("loop_interval", message.getLoopInterval());
            params.put("loop_times", message.getLoopTimes());
        }

        return callRestful(XingeApp.RESTAPI_PUSHSINGLEDEVICE, params);
    }

    /**
     * 推送给指定账号，限Android系统使用
     *
     * @param deviceType 设备类型，请填0
     * @param account    目标账号
     * @param message    待推送的消息
     * @return 服务器执行结果，JSON形式
     */
    public JSONObject pushSingleAccount(Context context, int deviceType, String account, Message message) throws JSONException {
        if (!ValidateMessageType(message)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("multi_pkg", message.getMultiPkg());
        params.put("device_type", deviceType);
        params.put("account", account);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        if (SharePreferenceUtil.isTestEnv(context)) {
            LogUtil.d("调用测试环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHSINGLEACCOUNT_TEST, params);
        } else {
            LogUtil.d("调用现网环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHSINGLEACCOUNT, params);
        }

    }

    /**
     * 推送给指定账号，限iOS系统使用
     *
     * @param deviceType  设备类型，请填0
     * @param account     目标账号
     * @param message     待推送的消息
     * @param environment 推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushSingleAccount(int deviceType, String account, MessageIOS message, int environment) throws JSONException {
        if (!ValidateMessageType(message, environment)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type or environment error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("device_type", deviceType);
        params.put("account", account);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("environment", environment);

        return callRestful(XingeApp.RESTAPI_PUSHSINGLEACCOUNT, params);
    }

    /**
     * 推送给多个账号，限Android设备使用 <br/>
     * 如果目标账号数超过10000，建议改用{@link #pushAccountListMultiple}接口
     *
     * @param deviceType  设备类型，请填0
     * @param accountList 目标账号列表
     * @param message     待推送的消息
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushAccountList(Context context,int deviceType, List<String> accountList, Message message) throws JSONException {
        if (!ValidateMessageType(message)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("multi_pkg", message.getMultiPkg());
        params.put("device_type", deviceType);
        params.put("account_list", new JSONArray(accountList).toString());
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);


        if (SharePreferenceUtil.isTestEnv(context)) {
            LogUtil.d("调用测试环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHACCOUNTLIST_TEST, params);
        } else {
            LogUtil.d("调用现网环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHACCOUNTLIST, params);
        }
    }

    /**
     * 推送给多个账号，限iOS设备使用 <br/>
     * 如果目标账号数超过10000，建议改用{@link #pushAccountListMultiple}接口
     *
     * @param deviceType  设备类型，请填0
     * @param accountList 目标账号列表
     * @param message     待推送的消息
     * @param environment 推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushAccountList(int deviceType, List<String> accountList, MessageIOS message, int environment) throws JSONException {
        if (!ValidateMessageType(message, environment)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type or environment error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("device_type", deviceType);
        params.put("account_list", new JSONArray(accountList).toString());
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("environment", environment);

        return callRestful(XingeApp.RESTAPI_PUSHACCOUNTLIST, params);
    }

    /**
     * 推送给全量设备，限Android系统使用
     *
     * @param deviceType 请填0
     * @param message    待推送的消息
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushAllDevice(Context context,int deviceType, Message message) throws JSONException {
        if (!ValidateMessageType(message)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("multi_pkg", message.getMultiPkg());
        params.put("device_type", deviceType);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0) {
            params.put("loop_interval", message.getLoopInterval());
            params.put("loop_times", message.getLoopTimes());
        }


        if (SharePreferenceUtil.isTestEnv(context)) {
            LogUtil.d("调用测试环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHALLDEVICE_TEST, params);
        } else {
            LogUtil.d("调用现网环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHALLDEVICE, params);
        }
    }

    /**
     * 推送给全量设备，限Android系统使用
     *
     * @param deviceType 请填0
     * @param message    待推送的消息
     * @param calType    0-采用离线计算，1-采用实时计算
     * @throws JSONException
     */
    public JSONObject pushAllDevice(int deviceType, Message message, int calType) throws JSONException {
        if (!ValidateMessageType(message)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("cal_type", calType);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("multi_pkg", message.getMultiPkg());
        params.put("device_type", deviceType);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0) {
            params.put("loop_interval", message.getLoopInterval());
            params.put("loop_times", message.getLoopTimes());
        }

        return callRestful(XingeApp.RESTAPI_PUSHALLDEVICE, params);
    }

    /**
     * 推送给全量设备，限iOS系统使用
     *
     * @param deviceType  设备类型，请填0
     * @param message     待推送的消息
     * @param environment 推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushAllDevice(int deviceType, MessageIOS message, int environment) throws JSONException {
        if (!ValidateMessageType(message, environment)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type or environment error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("device_type", deviceType);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("environment", environment);

        if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0) {
            params.put("loop_interval", message.getLoopInterval());
            params.put("loop_times", message.getLoopTimes());
        }

        return callRestful(XingeApp.RESTAPI_PUSHALLDEVICE, params);
    }

    /**
     * 推送给全量设备，限iOS系统使用
     *
     * @param deviceType  设备类型，请填0
     * @param message     待推送的消息
     * @param environment 推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @param calType     0-采用离线计算，1-采用实时计算
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushAllDevice(int deviceType, MessageIOS message, int calType, int environment) throws JSONException {
        if (!ValidateMessageType(message, environment)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type or environment error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("cal_type", calType);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("device_type", deviceType);
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("environment", environment);

        if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0) {
            params.put("loop_interval", message.getLoopInterval());
            params.put("loop_times", message.getLoopTimes());
        }

        return callRestful(XingeApp.RESTAPI_PUSHALLDEVICE, params);
    }

    /**
     * 推送给多个tags对应的设备，限Android系统使用
     *
     * @param deviceType 设备类型，请填0
     * @param tagList    指定推送的tag列表
     * @param tagOp      多个tag的运算关系，取值必须是下面之一： AND OR
     * @param message    待推送的消息
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushTags(Context context, int deviceType, List<String> tagList, String tagOp, Message message) throws JSONException {
        if (!ValidateMessageType(message)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type error!'}");
        }
        if (!message.isValid() || tagList.size() == 0 || (!tagOp.equals("AND") && !tagOp.equals("OR"))) {
            return new JSONObject("{'ret_code':-1,'err_msg':'param invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("multi_pkg", message.getMultiPkg());
        params.put("device_type", deviceType);
        params.put("message_type", message.getType());
        params.put("tags_list", new JSONArray(tagList).toString());
        params.put("tags_op", tagOp);
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0) {
            params.put("loop_interval", message.getLoopInterval());
            params.put("loop_times", message.getLoopTimes());
        }

        if (SharePreferenceUtil.isTestEnv(context)) {
            LogUtil.d("调用测试环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHTAGS_TEST, params);
        } else {
            LogUtil.d("调用现网环境OPEN_api");
            return callRestful(XingeApp.RESTAPI_PUSHTAGS, params);
        }
    }

    /**
     * 推送给多个tags对应的设备，限iOS系统使用
     *
     * @param deviceType  设备类型，请填0
     * @param tagList     指定推送的tag列表
     * @param tagOp       多个tag的运算关系，取值必须是下面之一： AND OR
     * @param message     待推送的消息
     * @param environment 推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushTags(int deviceType, List<String> tagList, String tagOp, MessageIOS message, int environment) throws JSONException {
        if (!ValidateMessageType(message, environment)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type or environment error!'}");
        }
        if (!message.isValid() || tagList.size() == 0 || (!tagOp.equals("AND") && !tagOp.equals("OR"))) {
            return new JSONObject("{'ret_code':-1,'err_msg':'param invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("send_time", message.getSendTime());
        params.put("device_type", deviceType);
        params.put("message_type", message.getType());
        params.put("tags_list", new JSONArray(tagList).toString());
        params.put("tags_op", tagOp);
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("environment", environment);

        if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0) {
            params.put("loop_interval", message.getLoopInterval());
            params.put("loop_times", message.getLoopTimes());
        }

        return callRestful(XingeApp.RESTAPI_PUSHTAGS, params);
    }

    /**
     * 创建大批量推送消息，后续可调用{@link #pushAccountListMultiple}或{@link #pushDeviceListMultiple}接口批量添加设备，限Android系统使用<br/>
     * 此接口创建的任务不支持定时推送
     *
     * @param message 待推送的消息
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject createMultipush(Message message) throws JSONException {
        if (!ValidateMessageType(message)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("multi_pkg", message.getMultiPkg());
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_CREATEMULTIPUSH, params);
    }

    /**
     * 创建大批量推送消息，后续可调用{@link #pushAccountListMultiple}或{@link #pushDeviceListMultiple}接口批量添加设备，限iOS系统使用<br/>
     * 此接口创建的任务不支持定时推送
     *
     * @param message     待推送的消息
     * @param environment 推送的目标环境 必须是其中一种： {@link #IOSENV_PROD}生产环境 {@link #IOSENV_DEV}开发环境
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject createMultipush(MessageIOS message, int environment) throws JSONException {
        if (!ValidateMessageType(message, environment)) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message type or environment error!'}");
        }
        if (!message.isValid()) {
            return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("expire_time", message.getExpireTime());
        params.put("message_type", message.getType());
        params.put("message", message.toJson());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("environment", environment);

        return callRestful(XingeApp.RESTAPI_CREATEMULTIPUSH, params);
    }

    /**
     * 推送消息给大批量账号，可对同一个pushId多次调用此接口，限Android系统使用 <br/>
     * 建议用户采用此接口自行控制发送时间
     *
     * @param pushId      {@link #createMultipush}返回的push_id
     * @param accountList 账号列表，数量最多为1000个
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushAccountListMultiple(long pushId, List<String> accountList) throws JSONException {
        if (pushId <= 0) {
            return new JSONObject("{'ret_code':-1,'err_msg':'pushId invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("push_id", pushId);
        params.put("account_list", new JSONArray(accountList).toString());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_PUSHACCOUNTLISTMULTIPLE, params);
    }

    /**
     * 推送消息给大批量设备，可对同一个pushId多次调用此接口，限Android系统使用 <br/>
     * 建议用户采用此接口自行控制发送时间
     *
     * @param pushId     {@link #createMultipush}返回的push_id
     * @param deviceList 设备列表，数量最多为1000个
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject pushDeviceListMultiple(long pushId, List<String> deviceList) throws JSONException {
        if (pushId <= 0) {
            return new JSONObject("{'ret_code':-1,'err_msg':'pushId invalid!'}");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("push_id", pushId);
        params.put("device_list", new JSONArray(deviceList).toString());
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_PUSHDEVICELISTMULTIPLE, params);
    }

    /**
     * 查询群发消息的状态，可同时查询多个pushId状态
     *
     * @param pushIdList 各类推送任务返回的push_id，可以一次查询多个
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryPushStatus(List<String> pushIdList) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("timestamp", System.currentTimeMillis() / 1000);
        JSONArray jArray = new JSONArray();
        for (String pushId : pushIdList) {
            JSONObject js = new JSONObject();
            js.put("push_id", pushId);
            jArray.put(js);
        }
        params.put("push_ids", jArray.toString());

        return callRestful(XingeApp.RESTAPI_QUERYPUSHSTATUS, params);
    }

    /**
     * 查询应用覆盖的设备数
     *
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryDeviceCount() throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_QUERYDEVICECOUNT, params);
    }

    /**
     * 查询应用当前所有的tags
     *
     * @param start 从哪个index开始
     * @param limit 限制结果数量，最多取多少个tag
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryTags(int start, int limit) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("start", start);
        params.put("limit", limit);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_QUERYTAGS, params);
    }

    /**
     * 查询应用所有的tags，如果超过100个，取前100个
     *
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryTags() throws JSONException {
        return queryTags(0, 100);
    }

    /**
     * 查询带有指定tag的设备数量
     *
     * @param tag 指定的标签
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryTagTokenNum(String tag) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("tag", tag);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_QUERYTAGTOKENNUM, params);
    }

    /**
     * 查询设备下所有的tag
     *
     * @param deviceToken 目标设备token
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryTokenTags(String deviceToken) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("device_token", deviceToken);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_QUERYTOKENTAGS, params);
    }

    /**
     * 取消尚未推送的定时任务
     *
     * @param pushId 各类推送任务返回的push_id
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject cancelTimingPush(String pushId) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("push_id", pushId);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_CANCELTIMINGPUSH, params);
    }

    /**
     * 批量为token设备标签，每次调用最多输入20个pair
     *
     * @param tagTokenPairs 指定token对应的指定tag
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject BatchSetTag(List<TagTokenPair> tagTokenPairs) throws JSONException {

        for (TagTokenPair pair : tagTokenPairs) {
            if (!this.ValidateToken(pair.token)) {
                String returnMsgJsonStr = String.format("{\"ret_code\":-1,\"err_msg\":\"invalid token %s\"}", pair.token);
                return new JSONObject(returnMsgJsonStr);
            }
        }

        Map<String, Object> params = this.InitParams();

        List<List> tag_token_list = new ArrayList<List>();

        for (TagTokenPair pair : tagTokenPairs) {
            List<String> singleTagToken = new ArrayList<String>();
            singleTagToken.add(pair.tag);
            singleTagToken.add(pair.token);

            tag_token_list.add(singleTagToken);
        }

        params.put("tag_token_list", new JSONArray(tag_token_list).toString());

        return callRestful(XingeApp.RESTAPI_BATCHSETTAG, params);
    }

    /**
     * 批量为token删除标签，每次调用最多输入20个pair
     *
     * @param tagTokenPairs 指定token对应的指定tag
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject BatchDelTag(List<TagTokenPair> tagTokenPairs) throws JSONException {

        for (TagTokenPair pair : tagTokenPairs) {
            if (!this.ValidateToken(pair.token)) {
                String returnMsgJsonStr = String.format("{\"ret_code\":-1,\"err_msg\":\"invalid token %s\"}", pair.token);
                return new JSONObject(returnMsgJsonStr);
            }
        }

        Map<String, Object> params = this.InitParams();

        List<List> tag_token_list = new ArrayList<List>();

        for (TagTokenPair pair : tagTokenPairs) {
            List<String> singleTagToken = new ArrayList<String>();
            singleTagToken.add(pair.tag);
            singleTagToken.add(pair.token);

            tag_token_list.add(singleTagToken);
        }

        params.put("tag_token_list", new JSONArray(tag_token_list).toString());

        return callRestful(XingeApp.RESTAPI_BATCHDELTAG, params);
    }

    /**
     * 查询token相关的信息，包括最近一次活跃时间，离线消息数等
     *
     * @param deviceToken 目标设备token
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryInfoOfToken(String deviceToken) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("device_token", deviceToken);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_QUERYINFOOFTOKEN, params);
    }

    /**
     * 查询账号绑定的token
     *
     * @param account 目标账号
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject queryTokensOfAccount(String account) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("account", account);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_QUERYTOKENSOFACCOUNT, params);
    }

    /**
     * 删除指定账号和token的绑定关系（token仍然有效）
     *
     * @param account     目标账号
     * @param deviceToken 目标设备token
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject deleteTokenOfAccount(String account, String deviceToken) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("account", account);
        params.put("device_token", deviceToken);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_DELETETOKENOFACCOUNT, params);
    }

    /**
     * 删除指定账号绑定的所有token（token仍然有效）
     *
     * @param account 目标账号
     * @return 服务器执行结果，JSON形式
     * @throws JSONException
     */
    public JSONObject deleteAllTokensOfAccount(String account) throws JSONException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_id", this.m_accessId);
        params.put("account", account);
        params.put("timestamp", System.currentTimeMillis() / 1000);

        return callRestful(XingeApp.RESTAPI_DELETEALLTOKENSOFACCOUNT, params);
    }
}
