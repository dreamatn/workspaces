def test_args_kwargs(arg1,arg2,arg3):
    print("args1 : ", arg1)
    print("args2 : ", arg2)
    print("args3 : ", arg3)

args=(1,2,3)
test_args_kwargs(*args)
kwargs={"arg1":'111',"arg2":'222',"arg3":'333'}
test_args_kwargs(**kwargs)