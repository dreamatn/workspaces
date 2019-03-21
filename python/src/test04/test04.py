queue = []

def enQ():
    queue.append(raw_input("Enter new string: ").strip())
def deQ():
    if len(queue) != 0:
        print 'Removed ,[',queue.pop(0),']'
    else:
        print 'Cannot pop from an empty queue!'
def viewQ():
    print queue

CMDs = {'e':enQ, 'd':deQ, 'v':viewQ}

def showMenu():
    pr = '''
(E)nqueue
(D)equeue
(V)iew
(Q)uit

Enter choice: '''

    while(True):
        while(True):
            try:
                choice = raw_input(pr).strip()[0].lower()
            except(EOFError, KeyboardInterrupt, IndexError):
                choice = 'q'
            
            print '\nYou picked :[%s]' %choice
            if choice not in 'devq':
                print 'invaild option, try again'
            else:
                break
        if choice == 'q':
            break
        CMDs[choice]()
if __name__ == '__main__':
    showMenu()
