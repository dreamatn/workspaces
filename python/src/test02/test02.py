import types
def displayNumType(num):
    print num, 'is',
    if type(num) is types.IntType:
        print 'an integer'
    elif type(num) is types.FloatType:
        print 'an float'
    elif type(num) is types.LongType:
        print 'an long'
    elif type(num) is types.ComplexType:
        print 'an complex'
    else:
        print 'not a number at all!!'

displayNumType(9)
displayNumType(9.0)
displayNumType(9L)
displayNumType(9+1j)
displayNumType("string")
