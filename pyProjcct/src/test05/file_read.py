fileName = raw_input("Enter file name : ")
f = open(fileName, 'r')
allLines = f.readlines()
f.close()
for each in allLines:
    print repr(each)