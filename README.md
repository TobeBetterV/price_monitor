# price_monitor

用selenium对[数字尾巴二手区](https://www.dgtle.com/sale)的选定商品进行价格监测

监测新发布商品的标题，把选定商品通过Bark把消息push到手机。

![IMG_5073](README.assets/IMG_5073.PNG)

电脑上也会打印出信息。

![Snipaste_2021-11-01_19-57-45](README.assets/Snipaste_2021-11-01_19-57-45.png)

主程序 main.py 适用于Mac OS。

不想一直开着电脑的话，可以查看这篇文章[部署到Linux](https://juejin.cn/user/1451783954639693)。

./Windows内的main.py适用于Windows系统。

需要安装的第三方库：selenium、requests、simpleaudio

```
pip3 install requests
pip3 install selenium
pip3 install simpleaudio
```

iOS下载一个Bark，获取自己的推送地址，粘贴到代码里。

![IMG_5074](README.assets/IMG_5074.PNG)

![image-20211101194707023](README.assets/image-20211101194707023.png)

运行即可。

![IMG_5075](README.assets/IMG_5075.PNG)
