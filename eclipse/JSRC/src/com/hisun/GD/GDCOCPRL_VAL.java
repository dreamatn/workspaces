package com.hisun.GD;

public class GDCOCPRL_VAL {
    public GDCOCPRL_CNT[] CNT = new GDCOCPRL_CNT[5];
    public String RSEQ = " ";
    public String CTA_NO = " ";
    public String REF_NO = " ";
    public String BUSI_TYP = " ";
    public int EXP_DT = 0;
    public GDCOCPRL_VAL() {
        for (int i=0;i<5;i++) CNT[i] = new GDCOCPRL_CNT();
    }
}
