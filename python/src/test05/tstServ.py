import socket
from time import ctime

HOST = ''
PORT = '23567'
BUFSIZ = 1024
ADDR = (HOST, PORT)

ss = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
ss.bind(ADDR)

while True:
    print 'waiting for message...'
    dt, addr = ss.recvfrom(BUFSIZ)
    if not dt:
        break
    ss.sendto('[%s] %s' %(ctime(), dt))
    print '...received from and returned to : ', addr
ss.close()
