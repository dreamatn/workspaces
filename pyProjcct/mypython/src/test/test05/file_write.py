import os

fileName = raw_input("Enter file name : ")
fobj = open(fileName, 'w')
while True:
    aLine = raw_input("Enter a line ('.' to quit): ")
    if aLine != '.':
        fobj.write('%s%s'%(aLine, os.linesep))
    else:
        break
fobj.close()
