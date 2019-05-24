package com.hisun.BP;

public class BPCI2128 {
    public char FLG = ' ';
    public int SEQ = 0;
    public int APP_NO = 0;
    public int APP_BR = 0;
    public int UP_BR = 0;
    public String UP_TLR = " ";
    public char ALLOT_TP = ' ';
    public char APP_TYPE = ' ';
    public String CCY = " ";
    public double OUT_AMT = 0;
    public BPCI2128_PVAL_INF[] PVAL_INF = new BPCI2128_PVAL_INF[20];
    public BPCI2128() {
        for (int i=0;i<20;i++) PVAL_INF[i] = new BPCI2128_PVAL_INF();
    }
}
