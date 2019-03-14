class FooClass(object):
    """my very first class : FooClass"""
    version = 0.1 # class data attribute

    def __init__(self, nm='John doe'):
        """constructor"""
        self.name = nm
        print('Created a class instance for ', nm)

    def showName(self):
        """display instance attritude and class name"""
        print("Your name is ",self.name)
        print("My name is ", self.__class__.__name__)

    def showVer(self):
        """display class static attritude"""
        print("Your version is ", self.version)

fool = FooClass()
fool.showName()
fool.showVer()

