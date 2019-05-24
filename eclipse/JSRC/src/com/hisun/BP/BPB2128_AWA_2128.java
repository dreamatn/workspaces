package com.hisun.BP;

public class BPB2128_AWA_2128 {
    public char FLG = ' ';
    public short FLG_NO = 0;
    public int SEQ = 0;
    public short SEQ_NO = 0;
    public int APP_NO = 0;
    public short APP_NO_NO = 0;
    public int APP_BR = 0;
    public short APP_BR_NO = 0;
    public int UP_BR = 0;
    public short UP_BR_NO = 0;
    public String UP_TLR = " ";
    public short UP_TLR_NO = 0;
    public char ALLOT_TP = ' ';
    public short ALLOT_TP_NO = 0;
    public char APP_TYPE = ' ';
    public short APP_TYPE_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public double OUT_AMT = 0;
    public short OUT_AMT_NO = 0;
    public BPB2128_PVAL_INF[] PVAL_INF = new BPB2128_PVAL_INF[20];
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public BPB2128_AWA_2128() {
        for (int i=0;i<20;i++) PVAL_INF[i] = new BPB2128_PVAL_INF();
    }
}
