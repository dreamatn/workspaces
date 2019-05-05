# coding=utf-8
import time
import sys,importlib
from wxpy import *
importlib.reload(sys)

bot = Bot()

my_friend = bot.friends()
for i in range(1, len(my_friend)):
    time.sleep(0.5)  # 延时根据检测频率限制而定
    print('-----%d/%d-------' % (i, len(my_friend)))
    my_friend[i].send_msg(" ॣ ॣ ॣ")

