package com.hisun.BP;

public class BPB2123_AWA_2123 {
    public int UP_BR = 0;
    public short UP_BR_NO = 0;
    public char APP_TYPE = ' ';
    public short APP_TYPE_NO = 0;
    public int APP_DT = 0;
    public short APP_DT_NO = 0;
    public double APP_AMT = 0;
    public short APP_AMT_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public BPB2123_PVAL_INF[] PVAL_INF = new BPB2123_PVAL_INF[20];
    public char ALLOT_TP = ' ';
    public short ALLOT_TP_NO = 0;
    public String UP_TLR = " ";
    public short UP_TLR_NO = 0;
    public String APP_NOTE = " ";
    public short APP_NOTE_NO = 0;
    public BPB2123_AWA_2123() {
        for (int i=0;i<20;i++) PVAL_INF[i] = new BPB2123_PVAL_INF();
    }
}
