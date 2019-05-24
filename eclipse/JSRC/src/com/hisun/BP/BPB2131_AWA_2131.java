package com.hisun.BP;

public class BPB2131_AWA_2131 {
    public double OUT_AMT = 0;
    public short OUT_AMT_NO = 0;
    public BPB2131_PVAL_INF[] PVAL_INF = new BPB2131_PVAL_INF[20];
    public String APP_G = " ";
    public short APP_G_NO = 0;
    public BPB2131_AWA_2131() {
        for (int i=0;i<20;i++) PVAL_INF[i] = new BPB2131_PVAL_INF();
    }
}
