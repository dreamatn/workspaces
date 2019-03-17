def displayNumType(num):
    print(num, 'is')
    if isinstance(num, (int, float, complex)):
        print("a number type:", type(num).__name__)
    else:
        print("not a number type at all")

displayNumType(-69)
displayNumType(98.6)
displayNumType(-5.2+1.9j)
displayNumType('xxx')