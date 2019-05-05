import itchat
from pandas import DataFrame
# 先登录
itchat.login()

# 获取好友列表
friends = itchat.get_friends(update=True)[0:]

# 初始化计数器，有男有女，当然，有些人是不填的
male = female = other = 0

# 遍历这个列表，列表里第一位是自己，所以从"自己"之后开始计算
# 1表示男性，2女性
# for i in friends[1:]:
#     sex = i["Sex"]
#     name = i["RemarkName"].encode('utf-8').decode('utf-8')
#     print(name)
#     male1=[]
#     female1=[]
#     other1=[]
#     if sex == 1:
#         male += 1
#         male1.append(name)
#     elif sex == 2:
#         female += 1
#         female1.append(name)
#     else:
#         other += 1
#         other1.append(name)

# # 总数算上，好计算比例啊～
# total = len(friends[1:])

# # 好了，打印结果
# print( "男性好友：%.2f%%" % (float(male) / total * 100))
# print( "女性好友：%.2f%%" % (float(female) / total * 100))
# print( "其他：%.2f%%" % (float(other) / total * 100))
# print("男性有：",male1)
# print("女性有：",female1)
# print("其他有：",other1)
def get_var(var):
    variable = []
    for i in friends:
        value = i[var]
        if type(value) != int:
            value = value.encode('utf-8')
        variable.append(value)
    return variable
Nickname = get_var('NickName')
#print Nickname
Sex = get_var('Sex')
Province = get_var('Province')
City = get_var('City')
RemarkName = get_var('RemarkName') # 备注名
Signature = get_var('Signature')


data = {"NickName":Nickname,"Sex":Sex,"Province":Province,"City":City,"Signature":Signature,"RemarkName":RemarkName}
frame = DataFrame(data)
frame.to_csv('data.csv')
