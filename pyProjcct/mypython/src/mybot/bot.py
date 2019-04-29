# -*- coding: utf-8 -*-
import aiml
import os

mybot_path = './mybot'
# 切换到语料库所在的工作目录
os.chdir(mybot_path)
print(os.chdir(mybot_path))

mybot = aiml.Kernel()
mybot.learn("std_startup.xml")
mybot.respond("load aiml b")

while True:
    print(mybot.respond(input("Enter your message >> ")))