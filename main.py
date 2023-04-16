# coding:utf-8
import json
import time
import requests

# 函数实现发送信息的功能，信息内容体现在message参数中

#数字尾巴闲置商品API
BASE_URL = 'https://www.dgtle.com/sale/getList/0?page=1&last_id=0'

# 获取数据
def get_href():
    try:
        goods_response= requests.get(BASE_URL)
        goods_object = json.loads(goods_response.text)

    except:
        print('无网络，十秒后重试...')
        time.sleep(10)
        get_href()
    #遍历列表，获取‘good_info’
    for good_info in goods_object['data']['dataList']:
        #判断是否是新商品
        if not good_info['id'] in history_goods_info_list:
            history_goods_info_list.append(good_info['id'])
            judge_info(good_info)

def judge_info(good_info):
    message = ['获取失败']
    try:
        title = good_info['title']
        price = good_info['price']
        # 遍历目标词
        for goal_string in goal_strings:
            # 检查标题是否包含目标词
            if goal_string in title and float(price[1:]) <= price_high:
                # 标题、价格、链接,用于 Push
                message = [title, price, good_info['id']]
                print(message)
                # Push
                push_it(message)
                audio_play()
                time.sleep(.2)

    except Exception as e:
        print(message , good_info)
    pass
# Push 到手机
def push_it(message):
    title = message[0]
    price = message[1]
    url = 'https://www.dgtle.com/sale-' + message[2] + '-1.html'
    try:
        requests.get('https://api.day.app/*****你自己的标识符*****/'+title+'/'+price+'?url='+url)
        print('已推送')

    except Exception as e:
        print(title + '推送失败')

def audio_play():
    # wave_obj = simpleaudio.WaveObject.from_wave_file("./Chord.wav")
    # wave_obj.play()
    pass

# 将符合条件的项目ID存入list变量中，便于查重
history_goods_info_list = []
# 目标词（用于检查标题是否包含）
goal_strings = ['switch','Switch','ns','美版','群晖','Macbook']
# 设置最低价格
price_high = 10000
# 开启浏览器
len_history_list = len(history_goods_info_list)
while True:
    # 获取href
    get_href()
    this_time = time.asctime( time.localtime(time.time()))[11:16]
    print('截至'+this_time+ '，历史列表里共 ' + str(len(history_goods_info_list)) + '个，其中增加 ' +str(len(history_goods_info_list) - len_history_list) + '个')
    len_history_list = len(history_goods_info_list)
    time.sleep(2)
