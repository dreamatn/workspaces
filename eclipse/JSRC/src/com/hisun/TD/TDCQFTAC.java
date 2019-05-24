package com.hisun.TD;

public class TDCQFTAC {
    public String CI_NO = " ";
    public String TRA_TYP = " ";
    public short TOT_NUM = 0;
    public TDCQFTAC_OUTINF[] OUTINF = new TDCQFTAC_OUTINF[200];
    public TDCQFTAC() {
        for (int i=0;i<200;i++) OUTINF[i] = new TDCQFTAC_OUTINF();
    }
}
