package com.hisun.LN;

public class LNCI4500 {
    public String RC_APP = " ";
    public short RC_CODE = 0;
    public String CTA_NO = " ";
    public int BR = 0;
    public String CI_NO = " ";
    public String CI_CNM = " ";
    public String PROD_CD = " ";
    public String CCY = " ";
    public double BAL = 0;
    public double BALANCE = 0;
    public int TR_DATE = 0;
    public String APT_REF = " ";
    public double TOT_AMT = 0;
    public double P_AMT = 0;
    public double I_AMT = 0;
    public double O_AMT = 0;
    public double L_AMT = 0;
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
    public LNCI4500_ACAMT[] ACAMT = new LNCI4500_ACAMT[5];
    public LNCI4500_PART_DAT[] PART_DAT = new LNCI4500_PART_DAT[10];
    public String CCY_TYPE = " ";
    public LNCI4500() {
        for (int i=0;i<5;i++) ACAMT[i] = new LNCI4500_ACAMT();
        for (int i=0;i<10;i++) PART_DAT[i] = new LNCI4500_PART_DAT();
    }
}
