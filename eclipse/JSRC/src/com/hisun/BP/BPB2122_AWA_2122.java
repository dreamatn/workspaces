package com.hisun.BP;

public class BPB2122_AWA_2122 {
    public int APP_NO = 0;
    public short APP_NO_NO = 0;
    public char ALLOT_TP = ' ';
    public short ALLOT_TP_NO = 0;
    public int END_DT = 0;
    public short END_DT_NO = 0;
    public int BEG_DT = 0;
    public short BEG_DT_NO = 0;
    public char APP_STS = ' ';
    public short APP_STS_NO = 0;
    public char APP_TYPE = ' ';
    public short APP_TYPE_NO = 0;
    public int UP_BR = 0;
    public short UP_BR_NO = 0;
    public int APP_BR = 0;
    public short APP_BR_NO = 0;
    public char FLG = ' ';
    public short FLG_NO = 0;
    public int APP_DT = 0;
    public short APP_DT_NO = 0;
    public double APP_AMT = 0;
    public short APP_AMT_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public BPB2122_PVAL_INF[] PVAL_INF = new BPB2122_PVAL_INF[20];
    public String APP_G = " ";
    public short APP_G_NO = 0;
    public BPB2122_AWA_2122() {
        for (int i=0;i<20;i++) PVAL_INF[i] = new BPB2122_PVAL_INF();
    }
}
