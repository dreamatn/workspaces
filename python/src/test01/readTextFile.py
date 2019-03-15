"readTextFile.py -- read and display text file"

# get file name
fname = input("Enter file name : ")
print 

# attempt to open file for reading
try:
    fobj = open(fname, 'r')
except:
    print("*** file open error")
else:
    # display contents to the screen 
    for eachLine in fobj:
        print(eachLine)
    fobj.close()