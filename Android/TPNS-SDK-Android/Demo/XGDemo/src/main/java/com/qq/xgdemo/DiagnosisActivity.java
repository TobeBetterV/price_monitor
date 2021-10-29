package com.qq.xgdemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qq.xg4all.R;
import com.qq.xgdemo.common.ExtendedListView;
import com.qq.xgdemo.common.ExtendedListView.OnPositionChangedListener;
import com.qq.xgdemo.exception.StepException;
import com.qq.xgdemo.javaPush.V3Push;
import com.qq.xgdemo.receiver.MessageReceiver;
import com.qq.xgdemo.util.LogUtil;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGLocalMessage;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGVipPushService;
import com.tencent.bigdata.baseapi.base.device.GuidInfoManager;
import com.tencent.bigdata.baseapi.base.util.CommonWorkingThread;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisActivity extends Activity implements OnPositionChangedListener {

	private DummyAdapter adapter;
	Handler handler = null;
	Message m = null;
	private ExtendedListView mListView;
	long currentTimeMillis = 0;
	String token;

	private boolean isForceCheck = true;
	private MsgReceiver msgReceiver;
    private int testStatus = 0;

    Handler errHandler = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diagnosis);

		msgReceiver = new MsgReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(MessageReceiver.TEST_ACTION);
		registerReceiver(msgReceiver, intentFilter);

		XGPushConfig.enableDebug(getApplicationContext(), true);
		//关闭第三方通道
		XGPushConfig.enableOtherPush(this,false);
		//小米通道
		XGPushConfig.setMiPushAppId(this, "2882303761517672364");
		XGPushConfig.setMiPushAppKey(this, "5271767210364");
		//魅族通道
		XGPushConfig.setMzPushAppId(this, "111888");
		XGPushConfig.setMzPushAppKey(this, "484a31262cf24dc9b568b5445d9e0495");

		Intent intent = getIntent();
		isForceCheck = intent.getBooleanExtra("isForceCheck", true);

		mListView = (ExtendedListView) findViewById(android.R.id.list);
		adapter = new DummyAdapter();
		adapter.setData(new ArrayList<String>());
		mListView.setAdapter(adapter);
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setOnPositionChangedListener(this);
		handler = new HandlerExtension(DiagnosisActivity.this);

        errHandler = new Handler();

		updateProgress("0ms 一键诊断程序开始执行......isForceCheck:" + isForceCheck);

		CommonWorkingThread.getInstance().execute(new Runnable() {
			@Override
			public void run() {

				step1();
			}
		}, 100L);

		findViewById(R.id.arrow).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private class DummyAdapter extends BaseAdapter {

		List<String> adapterData;

		@Override
		public int getCount() {
			return (null == adapterData ? 0 : adapterData.size());
		}

		public List<String> getData() {
			return adapterData;
		}

		public void setData(List<String> pushInfoList) {
			adapterData = pushInfoList;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(DiagnosisActivity.this).inflate(R.layout.list_item, parent,
						false);
			}

			TextView textView = (TextView) convertView;
			textView.setText(position + " --> " + adapterData.get(position));

			return convertView;
		}
	}

	@Override
	public void onPositionChanged(ExtendedListView listView, int firstVisiblePosition, View scrollBarPanel) {
		((TextView) scrollBarPanel).setText("Position " + firstVisiblePosition);
	}


	private void step1() {
		currentTimeMillis = System.currentTimeMillis();
		XGPushManager.registerPush(getApplicationContext(),
				new XGIOperateCallback() {
					@Override
					public void onSuccess(Object data, int flag) {
						errHandler.removeCallbacksAndMessages(null);
						token = data.toString();
						updateProgress((System.currentTimeMillis() - currentTimeMillis) + "ms 信鸽终端注册成功!");
						step2();
					}

					@Override
					public void onFail(Object data, int errCode, String msg) {
						errHandler.removeCallbacksAndMessages(null);
						StringBuffer sb = new StringBuffer((System.currentTimeMillis() - currentTimeMillis) + "ms 信鸽终端注册失败!\r\n").append(msg).append(errCode).append("!\r\n");
						sb.append(errCodeHandle(errCode));
						updateProgress("+++ register push failed. token:" + data);
						testError("REGISTER_PUSH:" + errCode);
					}
				});
		postError("REGISTER_PUSH_NO_RESP", 20000);
	}

	private void step2() {
		long timeMillis = System.currentTimeMillis();
		ActivityManager am = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningServiceInfo> runningServiceInfos = am.getRunningServices(Integer.MAX_VALUE);

		if (runningServiceInfos == null || runningServiceInfos.size() < 1) {
			updateProgress((System.currentTimeMillis() - timeMillis) + "ms 信鸽终端服务进程没有正常启动!");
			testError("PUSH_SERVICE_NOT_RUN1");
			return;
		}

		String PushServiceName = XGVipPushService.class.getName();
		for (RunningServiceInfo serviceInfo : runningServiceInfos) {
			if (PushServiceName.equals(serviceInfo.service.getClassName())) {
				updateProgress((System.currentTimeMillis() - timeMillis) + "ms 信鸽终端服务进程正常启动!");
				step3();
				return;
			}
		}

		updateProgress((System.currentTimeMillis() - timeMillis) + "ms 信鸽终端服务进程没有正常启动!");
		testError("PUSH_SERVICE_NOT_RUN2");
	}

	private void step3() {
        testStatus = Constants.TEST_LOCAL_NOTIFICATION;
        postError("TEST_LOCAL_NOTIFICATION");

		long timeMillis = System.currentTimeMillis();
		XGLocalMessage local_msg = new XGLocalMessage();
		local_msg.setType(1);
		local_msg.setTitle(Constants.LOCAL_NOTIFICATION_TITLE);
		local_msg.setContent("诊断程序-->本地通知测试");
		local_msg.setDate("20141119");
		local_msg.setHour("22");
		local_msg.setMin("34");
		XGPushManager.addLocalNotification(getApplicationContext(), local_msg);
		updateProgress((System.currentTimeMillis() - timeMillis) + "ms 信鸽终端添加本地通知成功!");
	}

	private void step4() {
		long timeMillis = System.currentTimeMillis();
		XGPushManager.clearLocalNotifications(getApplicationContext());
		updateProgress((System.currentTimeMillis() - timeMillis) + "ms 信鸽终端清理本地通知缓存成功!");
		step5();
	}

	private void step5() {
		testStatus = Constants.TEST_SET_TAG;
		postError("TEST_SET_TAG");
		long timeMillis = System.currentTimeMillis();
		XGPushManager.setTag(getApplicationContext(), Constants.TEST_TAG_NAME);
		updateProgress((System.currentTimeMillis() - timeMillis) + "ms 信鸽终端添加标签成功!");
	}

	private void step6() {
		testStatus = Constants.TEST_DEL_TAG;
		postError("TEST_DEL_TAG");
		long timeMillis = System.currentTimeMillis();
		XGPushManager.deleteTag(getApplicationContext(), "DiagnosisTag");
		updateProgress((System.currentTimeMillis() - timeMillis) + "ms 信鸽终端删除标签成功!");
	}

	private void step7() {
		testStatus = Constants.TEST_NOTIFICATION;
		postError("TEST_NOTIFICATION");
		final String token = GuidInfoManager.getToken(getApplicationContext());
		if (TextUtils.isEmpty(token)) {
			LogUtil.d("token 为空，请确认注册或本地缓存的token");
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				V3Push.pushSingleDevice(getApplicationContext(), "test title", token);
			}
		}).start();
	}

	private void testError(String stepMsg) {
		updateProgress("一键诊断程序执行过程出错！\r\n" + "出错步骤:" + stepMsg);
		if (isForceCheck) {
			StepException se =  new StepException("err:" + stepMsg);
			final RuntimeException re =  new RuntimeException(se);
			// 直接throw可能被信鸽的回调函数捕捉了，所以另起线程抛出异常
			CommonWorkingThread.getInstance().execute(new Runnable() {
				@Override
				public void run() {
					throw re;
				}
			});
		}
	}

	private void postError(final String errmsg) {
        postError(errmsg, 10000);
    }

	private void postError(final String errmsg, final long timeoutMs) {
		errHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				//testError(errmsg);
			}
		}, timeoutMs);
	}

	private void testSuccess() {
		updateProgress("一键诊断程序执行完毕! 成功！\r\n");
	}

	private static class HandlerExtension extends Handler {
		WeakReference<DiagnosisActivity> mActivity;

		HandlerExtension(DiagnosisActivity activity) {
			mActivity = new WeakReference<DiagnosisActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			DiagnosisActivity theActivity = mActivity.get();
			if (theActivity == null) {
				theActivity = new DiagnosisActivity();
			}
			if (msg != null) {
				Log.w("test", msg.obj.toString());
				ExtendedListView mListView = (ExtendedListView) theActivity.findViewById(android.R.id.list);
				DummyAdapter adapter = (DummyAdapter) mListView.getAdapter();
				adapter.getData().add(msg.obj.toString());
				adapter.notifyDataSetChanged();
			}
		}
	}

	private static String errCodeHandle(int errCode) {
		switch (errCode) {
		case -1:
			return "参数错误,请检查各字段是否合法!";
		case -2:
			return "请求时间戳不在有效期内!";
		case -3:
			return "sign校验无效，检查access id和secret key（注意不是access key）!";
		case 7:
			return "别名/账号绑定的终端数满了（10个）!";
		case 14:
			return "收到非法token，例如ios终端没能拿到正确的token!";
		case 15:
			return "信鸽逻辑服务器繁忙!";
		case 19:
			return "操作时序错误\r\n例如:进行tag操作前未获取到deviceToken 没有获取到deviceToken的原因: 1.没有注册信鸽或者苹果推送。 2.provisioning profile制作不正确。!";
		case 20:
			return "鉴权错误，可能是由于Access ID和Access Key不匹配,请检查1.AndroidMenifest.xml是否配置正确;2.云端环境是否是正式环境!";
		case 40:
			return "推送的token没有在信鸽中注册,请重新执行注册流程,或增加注册失败重试逻辑!并检查反注册逻辑是否使用不当?";
		case 48:
			return "推送的账号没有在信鸽中注册,请重新执行注册流程,或增加注册失败重试逻辑!";
		case 63:
			return "标签系统忙,请重新执行设置标签流程,或增加设置标签失败重试逻辑!";
		case 71:
			return "APNS服务器繁忙!";
		case 73:
			return "消息字符数超限,通知不超过75个字符,消息不超过4096字节!";
		case 76:
			return "请求过于频繁，请稍后再试!";
		case 100:
			return "APNS证书错误。请重新提交正确的证书!";
		case 2:
			return "参数错误，例如绑定了单字符的别名，或是ios的token长度不对，应为64个字符!";
		case 10000:
			return "起始错误!";
		case 10001:
			return "操作类型错误码，例如参数错误时将会发生该错误!";
		case 10002:
			return "正在执行注册操作时，又有一个注册操作到来，则回调此错误码!";
		case 10003:
			return "权限出错,请检查AndroidMenifest.xml权限是否配置齐全，详见http://developer.xg.qq.com/index.php/Android_SDK%E5%BF%AB%E9%80%9F%E6%8C%87%E5%8D%97!";
		case 10004:
			return "so出错,请检查工程是否导入libtpnsSecurity.so和libtpnsWatchdog.so,然后重新安装!";
		case 10100:
			return "当前网络不可用,请检查wifi或移动网络是否打开!";
		case 10101:
			return "创建链路失败,请重启应用!";
		case 10102:
			return "请求处理过程中， 链路被主动关闭!";
		case 10103:
			return "请求处理过程中，服务器关闭链接!";
		case 10104:
			return "请求处理过程中，客户端产生异常!";
		case 10105:
			return "请求处理过程中，发送或接收报文超时!";
		case 10106:
			return "请求处理过程中， 等待发送请求超时!";
		case 10107:
			return "请求处理过程中， 等待接收请求超时!";
		case 10108:
			return "服务器返回异常报文!";
		case 10109:
			return "未知异常，请在QQ群中直接联系管理员，或前往论坛发帖留言!";
		case 10110:
			return "创建链路的handler为null!";
		default:
			return "";
		}
	}

	private static Object getMetaData(Context paramContext, String name, Object defaultValue) throws NameNotFoundException {
		PackageManager packageManager = paramContext.getPackageManager();
		ApplicationInfo applicationInfo = packageManager
				.getApplicationInfo(paramContext.getPackageName(),
						PackageManager.GET_META_DATA);
		if (applicationInfo != null) {
			Object obj = applicationInfo.metaData.get(name);
			if (obj != null) {
				return obj;
			}
		}
		return defaultValue;
	}

	private void updateProgress(String mobj) {
		m = handler.obtainMessage();
		m.obj = mobj;
		m.sendToTarget();
	}

	@Override
	protected void onDestroy() {
		if (msgReceiver != null) {
			unregisterReceiver(msgReceiver);
		}
		super.onDestroy();
	}

	class MsgReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
            int stepMsg = intent.getIntExtra("step", 0);
			Log.i("xg.test", "onReceive: " + stepMsg);
            switch (stepMsg) {
            case Constants.TEST_LOCAL_NOTIFICATION:
                if (testStatus == Constants.TEST_LOCAL_NOTIFICATION) {
                    errHandler.removeCallbacksAndMessages(null);
                    step4();
                }
                break;
            case Constants.TEST_SET_TAG:
                if (testStatus == Constants.TEST_SET_TAG) {
                    errHandler.removeCallbacksAndMessages(null);
                    step6();
                }
                break;
            case Constants.TEST_DEL_TAG:
                if (testStatus == Constants.TEST_DEL_TAG) {
                    errHandler.removeCallbacksAndMessages(null);
					step7();
                }
                break;
            case Constants.TEST_NOTIFICATION:
                if (testStatus == Constants.TEST_NOTIFICATION) {
                    errHandler.removeCallbacksAndMessages(null);
					testSuccess();
                }
                break;
            default:
                break;
            }
		}
	}
}
