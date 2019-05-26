import cx_Oracle


def opt_fmt(dicts):
    print("111")
    arr = []
    for dct in dicts.items():
        tmp_var = dct[0] + "=" + repr(dct[1])
        arr.append(tmp_var)
    print(arr)
    return arr


# 查询
def sqlselect(sql, database):
    cr = database.cursor()
    cr.execute(sql)
    rs = cr.fetchall()
    cr.close()
    return rs


# 无参数增删改
def dml1(sql, database):
    cr = database.cursor()
    cr.execute(sql)
    cr.close()
    database.commit()


# 有参数增删改
def dml2(sql, database, **params):
#   trans_fmt = opt_fmt(params)
    print(params)
    cr = database.cursor()
    cr.execute(sql, params)
    cr.close()
    database.commit()


def main():
    username = "dev001"
    password = "123456"
    host = "127.0.0.1"
    orcl = "oracle12c"
    constr = username + "/" + password + "@" + host + "/" + orcl

    db = cx_Oracle.connect(constr)
    args = {'deptno': 50, 'dname': 'CITY', 'loc': 'BEIJING'}
    sqlstr1 = "INSERT INTO dept VALUES (:deptno, :dname, :loc);"
    dml2(sqlstr1, db, **args)
    sqlstr2 = "select * from dept"
#   rs = sqlselect(sqlstr2, db)
#   print("print all %s" % rs)


if __name__ == '__main__':
    main()
