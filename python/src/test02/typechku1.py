import types

def displayNumType(num):
    print(num, 'is',)
    if type(num) == types.IntType:
        print("an integer")
    elif type(num) == types.FloatType:
        print("an float")
    elif type(num) == types.ComplexType:
        print("an complex")
    else:
        print("not a num at all")

displayNumType(10)
displayNumType(10.1)
displayNumType(10+0j)
displayNumType("xxx")