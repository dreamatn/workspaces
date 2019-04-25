import re
m = re.search('\\[rtfvn]', r'Hello world!\n')
if m is not None:
    m.group()
