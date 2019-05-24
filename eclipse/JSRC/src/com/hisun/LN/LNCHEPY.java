package com.hisun.LN;

public class LNCHEPY {
    public String CTA = " ";
    public int BR = 0;
    public String CI_NO = " ";
    public String CI_CNM = " ";
    public String PROD_CD = " ";
    public String CCY = " ";
    public double PRINCIPAL = 0;
    public double BALANCE = 0;
    public int TR_VAL_DTE = 0;
    public double TOT_P_AMT = 0;
    public char INT_MODE = ' ';
    public double PC_RATE = 0;
    public double TOT_I_AMT = 0;
    public double PC_AMT = 0;
    public double TOT_AMT = 0;
    public long DISB_REF = 0;
    public LNCHEPY_ACAMT[] ACAMT = new LNCHEPY_ACAMT[5];
    public double TOT_LMT_AMT = 0;
    public char CTL_STS = ' ';
    public LNCHEPY() {
        for (int i=0;i<5;i++) ACAMT[i] = new LNCHEPY_ACAMT();
    }
}
