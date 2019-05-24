package com.hisun.LN;

public class LNCDDALN_COMM_DATA {
    public String CTA_NO = " ";
    public int BR = 0;
    public String CI_NO = " ";
    public String CI_CNM = " ";
    public String PROD_CD = " ";
    public String CCY = " ";
    public double PRINCIPAL = 0;
    public double BALANCE = 0;
    public int TR_VAL_DTE = 0;
    public String APT_REF = " ";
    public double TOT_AMT = 0;
    public double TOT_P_AMT = 0;
    public double TOT_I_AMT = 0;
    public double TOT_O_AMT = 0;
    public double TOT_L_AMT = 0;
    public String MMO = " ";
    public double HRG_AMT = 0;
    public char RDI_FLG = ' ';
    public double RDI_AMT = 0;
    public char ADJ_TYP = ' ';
    public String ADJ_AC = " ";
    public char MLT_STL = ' ';
    public char CUR_TRM = ' ';
    public char CLN_CUT = ' ';
    public char SUBS_FLG = ' ';
    public LNCDDALN_ACAMT[] ACAMT = new LNCDDALN_ACAMT[5];
    public String CCY_TYPE = " ";
    public LNCDDALN_COMM_DATA() {
        for (int i=0;i<5;i++) ACAMT[i] = new LNCDDALN_ACAMT();
    }
}
