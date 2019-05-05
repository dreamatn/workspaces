import pyautogui as pag

while True:
    # 截取屏幕，这只是缓存，如果要保存截图，可以带上参数，即要保存的路径
    pag.screenshot()
    # 在屏幕截图中找到微信联系人的按钮图标，找到的话返回坐标如(42, 405, 27, 28)，找不到返回 None
    pointLeft = pag.locateOnScreen('screens/left.png')
    print(pointLeft)
    if pointLeft is None:
        print("结束了")
        break
    # 找到按钮后，就能取得它的坐标，横坐标 +100，即在联系人上点击右键
    pag.rightClick(pointLeft[0] + 100, pointLeft[1])

    # 右键点击联系人之后会出现菜单，接着就要找到「删除聊天」的按钮位置
    pag.screenshot()
    pointDelete = pag.locateOnScreen('screens/delete.png')
    print(pointDelete)
    if pointDelete is None:
        print("结束了")
        break
    # 找到「删除聊天」之后，适当把鼠标指针向右、下移动 10 px，点击删除
    pag.click(pointDelete[0] + 10, pointDelete[1] + 10)
