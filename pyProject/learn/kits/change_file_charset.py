# coding: UTF-8
import sys
import os
import chardet
import codecs


def print_usage():
    print(
        '''usage:
        change_charset [file|directory] [charset] [output file]\n
        for example:
          change 1.txt utf-8 n1.txt
          change 1.txt utf-8
          change . utf-8
          change 1.txt
    ''')


def get_charset(file_name):
    f = codecs.open(file_name, 'rb')
    s = f.read()
    f.close()
    print(chardet.detect(s)['encoding'])
    return chardet.detect(s)['encoding']


def remove(file_name):
    os.remove(file_name)


def change_file_charset(file_name, output_file_name, charset):
    old_charset = get_charset(file_name)
    print("start")
    f = codecs.open(file_name, 'r', old_charset)
    s = f.read()
    print(s)
    f.close()

    if file_name == output_file_name or output_file_name == "":
        remove(file_name)

    if output_file_name == "":
        output_file_name = file_name
    f = codecs.open(output_file_name, 'w', charset)
    f.write(s)
    f.close()
    get_charset(file_name)
    print("end")


def do(file_name, output_file_name, charset):
    if os.path.isdir(file_name):
        for item in os.listdir(file_name):
            try:
                if os.path.isdir(file_name + "/" + item):
                    do(file_name + "/" + item, "", charset)

                else:
                    change_file_charset(file_name + "/" + item, "", charset)
            except OSError as e:
                print(e)

    else:
        change_file_charset(file_name, output_file_name, charset)


if __name__ == '__main__':
    # length = len(sys.argv)
    # print(sys.argv[1])
    length = 2
    filepath = 'D:\\test'

    if length == 1:
        print_usage()
    elif length == 2:
        # do(sys.argv[1], "", "utf-8")
        do(filepath, "", "utf-8")
    elif length == 3:
        do(sys.argv[1], "", sys.argv[2])
    elif length == 4:
        do(sys.argv[1], sys.argv[3], sys.argv[2])
    else:
        print_usage()
