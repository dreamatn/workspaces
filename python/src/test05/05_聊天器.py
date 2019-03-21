# 导报 socket
import socket

# 定义发送消息的函数
def send_msg(upd_socket):
    # 提示 输入 发送给对方的消息数据
    msg = input("请输入发送消息：")
    # 提示 输入  对方的IP
    ip = input("请输入对方的IP地址：")
    # 提示 输入 对方的端口地址
    port = int(input("请输入对方的端口地址："))
    # 准备接收方地址
    dest_addr = (ip,port)
    # 调用upd_socket发送数据的方法发送数据，注意别忘记编码
    # 这个方法接受两个参数，一个发送的数据（要是字节类型的），另一个是元组类型的里面装("ip",port)
    upd_socket.sendto(msg.encode("utf-8"),dest_addr)

# 定义接受消息的函数
def recv_msg(udp_socket):
    # 调用udp_socket接受数据的方法
    # 1024 是设定接受的最大数据，单位为字节
    recv_msg = udp_socket.recvfrom(1024)
    # 对方的IP和端口
    recv_ip = recv_msg[1]
    # 对方发送过来的数据，解码
    recv_data = recv_msg[0].decode("gbk")
    # 显示对方发送的数据
    print("%s vivi %s" % (recv_ip,recv_data))

# 定义main方法，在main方法中实现聊天器的功能
def main():
    # 1.初始化socket对象
    # socket.AF_INET INV4的类型
    # socket.SOCK_DGRAM 使用UDP协议
    udp_socket = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)

    # 2.绑定IP和端口，用于接受数据
    # ip地址不写 表示可以接受任何IP发送过来的数据
    # 注意：这个方法直接收一个参数，类型是元组，IP和端口必须写在一个元组中
    udp_socket.bind(("",8080))

    # 3.显示菜单
    print("-" * 30)
    print("1.发送消息")
    print("2.接收消息")
    print("-" * 30)

    while True:
        # 4.提示 请录入菜单编号
        menu_code= input("请输入菜单编号：")

        # 5.判断菜单编号，调用对应的功能
        if menu_code == "1":
            # 调用 发送消息的功能
            send_msg(udp_socket)
        elif menu_code == "2":
            # 调用接受消息的功能
            recv_msg(udp_socket)
        else:
            print("输入的菜单编号有误，请重新输入！")

    udp_socket.close()

# 调用main方法
if __name__ == '__main__':
    main()
            
