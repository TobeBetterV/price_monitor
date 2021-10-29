package com.qq.xgdemo.util;


import java.io.File;

public class Constants {


    //账号类
    public static final String XG_APP_ID = "d617a675b62d0";
    public static final Long XG_ACCESS_ID = 1500001533L;
    public static final String XG_ACCESS_KEY = "ATDGIJHH787R"; //正常版本的access key
    public static final String XG_SECRET_KEY = "euwhfy3zyi3hvahkf60y0c2xx3wlpsz1"; //正常版本的 secret_key

    public static final String XG_TEST_TAG = "agtestTag";
    public static final String XG_TEST_ACCOUNT = "agtestAccount";
    public static final String XG_TEST_ACCOUNT1 = "agtestAccount11";

    //存放自动化结果的目录
    public static final String FILE_DIR_FUNCTIONAL_INTERFACE = "FunctionalInterfaceTest" + File.separator;
    public static final String FILE_DIR_BASE_PUSH = "BasePush" + File.separator;
    public static final String FILE_DIR_PRESSURE = "PressureTest" + File.separator;
    public static final String FILE_DIR_PERFORMANCE = "Performance"+ File.separator;
    public static final String LOG_FILE_ROOT = "信鸽自动化" + File.separator + "xg4all" + File.separator + "Logs";
    public static final String RESULT_FILE_ROOT = "信鸽自动化" + File.separator + "xg4all" + File.separator + "Results";

    //google 相关的
    public static final String federationUrl = "https://fcm-stream.sandbox.google.com/v1/federation/iid";
    public static final String federationAppData = "Cj0KDWNvbS5xcS54ZzRhbGwSC2V5Z0hqanFDeHdrGNXCj5zxEiIKMjEwMDI3MTUzOSoMMjM1MTM4ODk2OTM4EAE";
    public static final String cachedToken = null;

    //测试结果
    public static final String PACKAGE_NAME = "com.qq.xg4all";
    public static final String TESTER = "agprince";
    public static final String PROJECT = "信鸽";
    public static final String OS = "Android";
    public static final int ANDROID_M = 22;

    //测试环境
    public static final String SCHEME = "http:";
    public static final String HOST = "//127.0.0.1";
    public static final String PORT = ":8080";
    public static final String PATH = "/device/test_result";

    //写文件类
    public static final String NA = "N/A";
    public static final String COMMA = ",";
    public static final String LINE_END = "\r\n";
    public static final String COLON = ":";


}
