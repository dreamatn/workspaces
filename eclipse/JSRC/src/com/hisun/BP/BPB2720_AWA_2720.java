package com.hisun.BP;

public class BPB2720_AWA_2720 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String REC_TLR = " ";
    public short REC_TLR_NO = 0;
    public int FLS_RECN = 0;
    public short FLS_RECN_NO = 0;
    public BPB2720_CCYS[] CCYS = new BPB2720_CCYS[20];
    public BPB2720_AWA_2720() {
        for (int i=0;i<20;i++) CCYS[i] = new BPB2720_CCYS();
    }
}
