# coding:utf-8

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
import time
import requests

# 函数实现发送信息的功能，信息内容体现在message参数中
#数字尾巴闲置页面url
BASE_URL = 'https://www.dgtle.com/sale'
chrome_options = Options()
#因为在云服务器上运行，去除可视界面，如需显示可视界面，将下面6行代码注释掉
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('window-size=1920x3000')
chrome_options.add_argument('--disable-gpu')
chrome_options.add_argument('--hide-scrollbars')
chrome_options.add_argument('blink-settings=imagesEnabled=false')
chrome_options.add_argument('--headless')

#开启浏览器
def start_chrome():
    driver = webdriver.Chrome(r'/usr/bin/chromedriver', options=chrome_options)
    driver.start_client()
    return driver
# 获取数据
def get_href():
    try:
        driver.get(BASE_URL)
        time.sleep(2)
    except:
        print('无网络，十秒后重试...')
        time.sleep(10)
        get_href()

    #闲置商品卡片css path
    goods_css_path = 'a.idle-content-list-1'
    # 用find_elements 返回商品列表，而 element 不可
    goods_card_list = driver.find_elements_by_css_selector(goods_css_path)

    #此列表保存商品‘href’
    goods_href_list = []
    #遍历列表，获取‘href’
    for goods_card in goods_card_list:
        goods_href = goods_card.get_attribute('href')
        goods_href_list.append(goods_href)

    for goods_href in goods_href_list:
        #判断是否是新商品
        if not goods_href in history_list:
            history_list.append(goods_href)
            judge_info(goods_href)

def judge_info(goods_href):
    driver.get(goods_href)
    title_css = '.title'
    price_css = '.idel_detail-left-2 > li:nth-child(1) > p:nth-child(2)'

    message = ['获取失败']
    try:
        title = driver.find_element_by_css_selector(title_css).text
        price = driver.find_element_by_css_selector(price_css).text
        # 遍历目标词
        for goal_string in goal_strings:
            # 检查标题是否包含目标词
            if goal_string in title and float(price[1:]) <= price_high:
                # 标题、价格、链接,用于 Push
                message = [title, price,goods_href]
                print(message)
                # Push
                push_it(message)
                audio_play()
                time.sleep(.2)

    except Exception as e:
        print(goods_href + message)
# Push 到手机
def push_it(repo_info):
    title = repo_info[0]
    url = ' ' +repo_info[2]
    message = repo_info[1]
    try:
        requests.get('https://api.day.app/*****填写自己的地址*****/'+title+'/'+message+'?url='+url)
        print('已推送')

    except Exception as e:
        print(repo_info[2] + '推送失败')

def audio_play():
    print('play none')

# 将符合条件的项目URL存入list变量中，便于查重
history_list = []
# 目标词（用于检查标题是否包含）
goal_strings = ['iPhone','Mini','pencil', '妙控','Magic','magic','Pencil']
# 设置最低价格
price_high = 10000
# 开启浏览器
driver = start_chrome()
len_history_list = len(history_list)
while True:
    # 获取href
    get_href()
    this_time = time.asctime( time.localtime(time.time()))[11:16]
    print('截至'+this_time+ '，历史列表里共 ' + str(len(history_list)) + '个，其中增加 ' +str(len(history_list) - len_history_list) + '个')
    len_history_list = len(history_list)
    time.sleep(2)

# while True:
#     # 获取项目列表，搜条件为最近一周、blockchain相关
#     project_list = get_project(last_week, topic)
#     for p in project_list:
#         stars = p['stargazers_count']
#         # 如果项目符合条件，则调用push_it函数，发送到手机上
#         if stars > 140 and not p['html_url'] in result:
#             message = 'The project '+ p['name'] + ' is qualified.' + ' URL: ' + p['html_url']
#             push_it(message)
#             result.append(p['html_url'])
#     # 设置休眠，每10分钟运行一次
#     time.sleep(600)
