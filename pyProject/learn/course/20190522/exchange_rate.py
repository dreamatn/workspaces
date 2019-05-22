print("****汇率兑换****")
RATE = 0.1449
mon_input = input("请输入金额和币种：")
# 获取币种
ccy_tp = mon_input[-3:]
# 获取金额
amt = eval(mon_input[:len(mon_input) - 3])
# 计算
if ccy_tp in ("CNY", "cny"):
    print("%f\t人民币 CNY\n%f\t美元 USD" % (amt, amt* RATE))
elif ccy_tp in ("USD", "usd"):
    print("%f\t美元 USD\n%f\t人民币 CNY" % (amt, amt / RATE))
else:
    print("币种输入有误")
