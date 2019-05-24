package com.hisun.BP;

public class BPB3290_AWA_3290 {
    public char VB_FLG = ' ';
    public short VB_FLG_NO = 0;
    public BPB3290_BV_INFO[] BV_INFO = new BPB3290_BV_INFO[10];
    public BPB3290_AWA_3290() {
        for (int i=0;i<10;i++) BV_INFO[i] = new BPB3290_BV_INFO();
    }
}
