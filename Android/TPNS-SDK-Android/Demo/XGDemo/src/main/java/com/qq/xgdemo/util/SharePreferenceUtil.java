package com.qq.xgdemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.preference.PreferenceManager;

public class SharePreferenceUtil {
	
	private static final String PREF_TOKEN = "deviceToken";
	private static final String PREF_TEST_ENV = "testEnv";
	private static final String PREF_FILE_NAME= "fileName";
	private static final String PREF_LOG_SERVCIE="logServcieStatus";
	private static final String PREF_SDCARD_STATE="externalStorageState";
	private static final String PREF_PUSH_VERSION = "javaPushVersion";
	private static final String PREF_PREFORMANCE_SERVICE="performanceServiceStatus";
	private static final String PREF_IS_FLOAT="isfloat";
	
	public static String getStoredToken(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_TOKEN, null);
	}
	
	@SuppressLint("NewApi")
	public static void setStoredToken(Context context,String token){
		PreferenceManager.getDefaultSharedPreferences(context)
		.edit().putString(PREF_TOKEN, token).apply();
	}

	public static boolean isTestEnv(Context context){
		LogUtil.d("获得网络环境状态");
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_TEST_ENV, false);
	}
	
	@SuppressLint("NewApi")
	public static void setTestEnv(Context context,boolean isTestBack){
		LogUtil.d( "设置网络环境状态");
		PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_TEST_ENV, isTestBack).apply();
	}
	public static String getPrefFileName(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_FILE_NAME,null);
	}
	public static void setPrefFileName(Context context, String fileName){
		PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREF_FILE_NAME,fileName).apply();
	}

	public static boolean isLogServiceStart(Context context){
		LogUtil.d("获取日志系统开启状态");
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_LOG_SERVCIE,false);
	}
	public static void setPrefLogServcie(Context context,boolean startLogService){
		PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_LOG_SERVCIE,startLogService).apply();
	}

	public static boolean isSdcardMounted(Context context){
		LogUtil.d("查看sdcard是否被授权写入文件");
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_SDCARD_STATE,false);
	}
	public static void setPrefSdcardState(Context context,boolean isSdcardMounted){
		PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_SDCARD_STATE,isSdcardMounted).apply();
	}

	public static void setPrefPushVersion(Context context,int version){
		PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(PREF_PUSH_VERSION,version).apply();
	}
	public static int getPrefPushVersion(Context context){
		return  PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_PUSH_VERSION,3);
	}

	//开启关闭日志
	public static void setPrefPreformanceService(Context context,boolean isPerformanceServiceStart){
		PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_PREFORMANCE_SERVICE,isPerformanceServiceStart).apply();
	}
	public static boolean getPrefPreformanceService(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_PREFORMANCE_SERVICE,false);
	}
	public static void setPrefIsFloat(Context context,boolean isFloat){
		PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_FLOAT,isFloat).apply();

	}
	public static boolean getPrefIsFloat(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_FLOAT,false);
	}


}
