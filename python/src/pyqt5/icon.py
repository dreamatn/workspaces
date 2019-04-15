import sys
from PyQt5 import QtWidgets,QtCore,QtGui

class Icon(QtWidgets.QWidget):
    def __init__(self, parent = None):
        QtWidgets.QWidget.__init__(self,parent)

        self.setGeometry(300,300,250,150)
        self.setWindowTitle("Icon")
        self.setWindowIcon(QtGui.QIcon("icons/web.png"))
        
app = QtWidgets.QApplication(sys.argv)
icon = Icon()
icon.show()
sys.exit(app.exec_())