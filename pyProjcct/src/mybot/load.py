# -*- coding: utf-8 -*-
import aiml
import sys
import os

def get_module_dir(name):
    path = getattr(sys.modules[name], '__file__', None)
    if not path:
        raise AttributeError('module %s has not attibute __file__' % name) 

alice_path = 'D:/dev_app/Python37/Lib/site-packages/aiml/botdata/alice'
# 切换到语料库目录
os.chdir(alice_path)

alice = aiml.Kernel()
alice.learn('startup.xml')
alice.respond('load alice')

while True:
    print(alice.respond(input('Enter your message >> ')))