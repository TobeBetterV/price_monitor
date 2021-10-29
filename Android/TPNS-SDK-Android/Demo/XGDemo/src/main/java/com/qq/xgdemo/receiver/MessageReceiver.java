package com.qq.xgdemo.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.qq.xgdemo.Constants;
import com.qq.xgdemo.common.NotificationService;
import com.qq.xgdemo.po.XGNotification;
import com.tencent.android.tpush.NotificationAction;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MessageReceiver extends XGPushBaseReceiver {
	public static final String UPDATE_LISTVIEW_ACTION = "com.qq.xgdemo.activity.UPDATE_LISTVIEW";
	public static final String TEST_ACTION = "com.qq.xgdemo.activity.TEST_ACTION";
	public static final String LogTag = "xg.test";

	/**
	 *  消息透传处理
	 * @param context
	 * @param message 解析自定义的 JSON
	 */
	@Override
	public void onTextMessage(Context context, XGPushTextMessage message) {
		String text = "收到消息:" + message.toString();
		// 获取自定义key-value
		String customContent = message.getCustomContent();
		if (customContent != null && customContent.length() != 0) {
			try {
				JSONObject obj = new JSONObject(customContent);
				// key1为前台配置的key
				if (!obj.isNull("key")) {
					String value = obj.getString("key");
					Log.d(LogTag, "get custom value:" + value);
				}
				// ...
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// APP自主处理消息的过程...
		Log.d(LogTag, text);
		show(context, text);
	}

	/**
	 * 通知展示
	 * @param context
	 * @param notifiShowedRlt 包含通知的内容
	 */
	@Override
	public void onNotificationShowedResult(Context context, XGPushShowedResult notifiShowedRlt) {
		if (context == null || notifiShowedRlt == null) {
			return;
		}
		XGNotification notific = new XGNotification();
		notific.setMsg_id(notifiShowedRlt.getMsgId());
		notific.setTitle(notifiShowedRlt.getTitle());
		notific.setContent(notifiShowedRlt.getContent());
		// notificationActionType==1为Activity，2为url，3为intent
		notific.setNotificationActionType(notifiShowedRlt
				.getNotificationActionType());
		// Activity,url,intent都可以通过getActivity()获得
		notific.setActivity(notifiShowedRlt.getActivity());
		notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime()));
		NotificationService.getInstance(context).save(notific);

		Intent testIntent = new Intent(TEST_ACTION);
		if (notifiShowedRlt.getTitle().equals(Constants.LOCAL_NOTIFICATION_TITLE)) {
			testIntent.putExtra("step", Constants.TEST_LOCAL_NOTIFICATION);
		} else {
			testIntent.putExtra("step", Constants.TEST_NOTIFICATION);
		}
		context.sendBroadcast(testIntent);

		Intent viewIntent = new Intent(UPDATE_LISTVIEW_ACTION);
		context.sendBroadcast(viewIntent);
		show(context, "您有1条新消息, " + "通知被展示 ， " + notifiShowedRlt.toString());
		Log.d(LogTag, "您有1条新消息, " + "通知被展示 ， " + notifiShowedRlt.toString() + ", PushChannel:" + notifiShowedRlt.getPushChannel());
	}

	/**
	 *  注册回调
	 * @param context
	 * @param errorCode 0 为成功，其它为错误码
	 */
	@Override
	public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult message) {
		if (context == null || message == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			// 在这里拿token
			String token = message.getToken();
			text = "注册成功1. token：" + token;
		} else {
			text = message + "注册失败，错误码：" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);
	}

	/**
	 *  反注册回调
	 * @param context
	 * @param errorCode 0 为成功，其它为错误码
	 */
	@Override
	public void onUnregisterResult(Context context, int errorCode) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "反注册成功";
		} else {
			text = "反注册失败" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

	}

	/**
	 * 设置标签回调
	 * @param context
	 * @param errorCode 0 为成功，其它为错误码
	 * @param tagName 设置的 TAG
	 */
	@Override
	public void onSetTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"设置成功";
		} else {
			text = "\"" + tagName + "\"设置失败,错误码：" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

		Intent testIntent = new Intent(TEST_ACTION);
		testIntent.putExtra("step", Constants.TEST_SET_TAG);
		context.sendBroadcast(testIntent);
	}

	/**
	 * 删除标签的回调
	 * @param context
	 * @param errorCode 0 为成功，其它为错误码
	 * @param tagName 设置的 TAG
	 */
	@Override
	public void onDeleteTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = "";
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"删除成功";
		} else {
			text = "\"" + tagName + "\"删除失败,错误码：" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

		Intent testIntent = new Intent(TEST_ACTION);
		testIntent.putExtra("step", Constants.TEST_DEL_TAG);
		context.sendBroadcast(testIntent);
	}

	/**
	 * 设置账号回调
	 * @param context
	 * @param errorCode 0 为成功，其它为错误码
	 * @param account 设置的账号
	 */
	@Override
	public void onSetAccountResult(Context context, int errorCode, String account) {
		Intent testIntent = new Intent(TEST_ACTION);
		testIntent.putExtra("step", Constants.TEST_SET_ACCOUNT);
		context.sendBroadcast(testIntent);
	}


	/**
	 * 删除账号回调
	 * @param context
	 * @param errorCode 0 为成功，其它为错误码
	 * @param account 设置的账号
	 */
	@Override
	public void onDeleteAccountResult(Context context, int errorCode, String account) {
		Intent testIntent = new Intent(TEST_ACTION);
		testIntent.putExtra("step", Constants.TEST_DEL_ACCOUNT);
		context.sendBroadcast(testIntent);
	}

	/**
	 * 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
	 * @param context
	 * @param message 包含被点击通知的内容
	 */
	@Override
	public void onNotificationClickedResult(Context context, XGPushClickedResult message) {
		if (context == null || message == null) {
			return;
		}
		String text = "";
		if (message.getActionType() == NotificationAction.clicked.getType()) {
			// 通知在通知栏被点击啦。。。。。
			// APP自己处理点击的相关动作
			// 这个动作可以在activity的onResume也能监听，请看第3点相关内容
			text = "通知被打开 :" + message;
		} else if (message.getActionType() == NotificationAction.delete.getType()) {
			// 通知被清除啦。。。。
			// APP自己处理通知被清除后的相关动作
			text = "通知被清除 :" + message;
		}
		Toast.makeText(context, "广播接收到通知被点击:" + message.toString(),
				Toast.LENGTH_SHORT).show();
		// 获取自定义key-value
		String customContent = message.getCustomContent();
		if (customContent != null && customContent.length() != 0) {
			try {
				JSONObject obj = new JSONObject(customContent);
				// key1为前台配置的key
				if (!obj.isNull("key")) {
					String value = obj.getString("key");
					Log.d(LogTag, "get custom value:" + value);
				}
				// ...
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// APP自主处理的过程。。。
		Log.d(LogTag, text);
		show(context, text);
	}

	private void show(Context context, String text) {
//		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

}
