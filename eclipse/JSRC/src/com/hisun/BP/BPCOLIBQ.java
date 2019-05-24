package com.hisun.BP;

public class BPCOLIBQ {
    public int APP_NO = 0;
    public String CCY = " ";
    public double APP_AMT = 0;
    public char FILLER4 = '01';
    public double OUT_AMT = 0;
    public char FILLER6 = '01';
    public BPCOLIBQ_PVAL_INFO[] PVAL_INFO = new BPCOLIBQ_PVAL_INFO[20];
    public BPCOLIBQ() {
        for (int i=0;i<20;i++) PVAL_INFO[i] = new BPCOLIBQ_PVAL_INFO();
    }
}
