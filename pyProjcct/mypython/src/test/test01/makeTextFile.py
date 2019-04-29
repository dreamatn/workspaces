"makeTextFile.py -- create text file"

import os
ls = os.linesep

# get file name
fname = input("Enter file name : ")
while True:
    if os.path.exists(fname):
        print("Error, %s already exists" % fname)
        fname = input("Enter file name again : ")
    else:
        break

# get file content lines
all = []
print("\nEnter lines ('.' by itself to quit).\n")

# loop until user terminates input
while True:
    entry = input("> ")
    if entry == ".":
        break
    else:
        all.append(entry)

# write lines to file with proper line-ending
fobj = open(fname, 'w')
fobj.writelines(['%s%s'%(x, ls) for x in all])
fobj.close()
print("DONE!")
