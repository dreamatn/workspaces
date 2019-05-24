package com.hisun.LN;

public class LNCSPREQ {
    public LNCSPREQ_RC RC = new LNCSPREQ_RC();
    public char FUN_CODE = ' ';
    public String CTA = " ";
    public int BR = 0;
    public String CI_NO = " ";
    public String CI_CNM = " ";
    public String PROD_CD = " ";
    public String PROD_DE = " ";
    public String CCY = " ";
    public double PRINCIPA = 0;
    public double BALANCE = 0;
    public double TOT_P_AMT = 0;
    public int TR_VAL_DTE = 0;
    public char INT_MODE = ' ';
    public double TOT_I_AMT = 0;
    public double PC_RATE = 0;
    public double PC_AMT = 0;
    public double TOT_AMT = 0;
    public double T_I_AMT = 0;
    public double T_O_AMT = 0;
    public double T_L_AMT = 0;
    public long DISB_REF = 0;
    public char SUB_TRAN = ' ';
    public double HRG_AMT = 0;
    public double TAX_AMT = 0;
    public double PROJ_INT = 0;
    public LNCSPREQ_SYLOAN_INFO[] SYLOAN_INFO = new LNCSPREQ_SYLOAN_INFO[10];
    public LNCSPREQ() {
        for (int i=0;i<10;i++) SYLOAN_INFO[i] = new LNCSPREQ_SYLOAN_INFO();
    }
}
