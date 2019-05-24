package com.hisun.GD;

public class GDCSCPRL_VAL {
    public String RSEQ = " ";
    public String CTA_NO = " ";
    public String REF_NO = " ";
    public String BUSI_TYP = " ";
    public int EXP_DT = 0;
    public int BR = 0;
    public GDCSCPRL_CNT[] CNT = new GDCSCPRL_CNT[5];
    public GDCSCPRL_VAL() {
        for (int i=0;i<5;i++) CNT[i] = new GDCSCPRL_CNT();
    }
}
