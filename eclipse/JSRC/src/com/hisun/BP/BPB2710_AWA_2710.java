package com.hisun.BP;

public class BPB2710_AWA_2710 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String REC_TLR = " ";
    public short REC_TLR_NO = 0;
    public int FLS_RECN = 0;
    public short FLS_RECN_NO = 0;
    public BPB2710_CCYS[] CCYS = new BPB2710_CCYS[20];
    public BPB2710_AWA_2710() {
        for (int i=0;i<20;i++) CCYS[i] = new BPB2710_CCYS();
    }
}
