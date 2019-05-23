import chardet
import codecs


def get_encoding(filepath):
    with codecs.open(filepath, 'rb') as f:
        data = f.read()
        f.close()
        print(chardet.detect(data))
        encd = chardet.detect(data)['encoding']
        return encd


def read_file(filepath):
    encoding = get_encoding(filepath)
    print("当前文件格式 : ", encoding)
    with codecs.open(filepath, 'r', encoding) as f:
        content = f.read()
        f.close()
        return content


def write_file(filepath, content):
    encoding = "UTF-8"
    with codecs.open(filepath, 'w', encoding) as f:
        f.write(content)
        f.close()


def transcoding(src, dst):
    content = read_file(src)
    write_file(dst, content)
    trancd = get_encoding(dst)

    print("转换后的文件格式 : ", trancd)


# orgd 文件路径
orgd = "D:/test.txt"
desd = "D:/test1.txt"
transcoding(orgd, desd)
