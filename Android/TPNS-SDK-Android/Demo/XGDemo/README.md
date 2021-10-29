## TPNS Demo
-------------------------------

### 使用方式
1. 在本地新建 AndroidStudio 项目工程，将 XGDemo 整个目录以模块形式导入到项目中。

2. 点击 Sync 按钮同步工程依赖。

3. 运行并安装 XGDemo 至手机，通过 demo 的功能按钮体验推送功能。 

### 厂商通道推送体验
>! 
>- XGDemo 包含华为、小米、魅族、fcm通道的推送配置，暂无 OPPO、vivo 通道的体验。但配置方法均类似，开发者可参照具体集成文档在自己的工程中进行配置。

1. 在 XGDemo 模块下 build.gradle 文件中取消 TPNS 各厂商推送依赖语句的注释，并同步工程。

2. 在 MainActivity.class.onCreate() 方法中取消以下代码的注释：
```
    //开启第三方通道
    XGPushConfig.enableOtherPush(this,true);
    //小米通道
    XGPushConfig.setMiPushAppId(this, "2882303761517672364");
    XGPushConfig.setMiPushAppKey(this, "5271767210364");
    /魅族通道
    XGPushConfig.setMzPushAppId(this, "111888");
    XGPushConfig.setMzPushAppKey(this, "484a31262cf24dc9b568b5445d9e0495");
```

3. 运行并安装 XGDemo 至手机。
