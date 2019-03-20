import string

alphas = string.letters + '_'
nums = string.digits
first_sym_msg = 'invalid : first symbol must be alphabetic'
remain_sym_msg = 'invalid : remaining symbols must be alphanumeric'
ok_msg = 'okay as an identifier'

print 'Welcome to Identifier Checker v1.0'
print 'Testees must be at least 2 chars long.'
while(True):
    myInput = raw_input('Please input Identifier : ')

    if len(myInput) > 1:
        if myInput[0] not in alphas:
            print first_sym_msg
        else:
            for otherChar in myInput[1:]:
                if otherChar not in alphas + nums:
                    print remain_sym_msg
            else:
                print ok_msg
