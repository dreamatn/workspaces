package com.hisun.BP;

public class BPB2090_AWA_2090 {
    public int APP_NO = 0;
    public short APP_NO_NO = 0;
    public char APP_STS = ' ';
    public short APP_STS_NO = 0;
    public int UP_BR = 0;
    public short UP_BR_NO = 0;
    public int APP_BR = 0;
    public short APP_BR_NO = 0;
    public int APP_DT = 0;
    public short APP_DT_NO = 0;
    public BPB2090_APP_INFO[] APP_INFO = new BPB2090_APP_INFO[5];
    public BPB2090_AWA_2090() {
        for (int i=0;i<5;i++) APP_INFO[i] = new BPB2090_APP_INFO();
    }
}
