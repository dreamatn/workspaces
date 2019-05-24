from turtle import Turtle

"""
    名称：绘制五角星
    作者：dbwave
    版本：1.0
    时间：5/23/219
"""


# draw 根据给定长度，绘制五角星
def draw(size):
    cnt = 1
    t = Turtle()
    while cnt <= 5:
        t.forward(size)
        t.right(144)
        cnt += 1


# main 主函数
def main():
    cnt = 1
    while cnt <= 5:
        length = cnt * 20
        draw(length)
        cnt += 1


if __name__ == "__main__":
    main()
