package com.hisun.LN;

public class LNCI4000 {
    public String CTA_NO = " ";
    public int BR = 0;
    public String CI_NO = " ";
    public String CI_CNM = " ";
    public String PROD_CD = " ";
    public String PROD_DE = " ";
    public String CCY = " ";
    public double LON_PRIN = 0;
    public double LON_BAL = 0;
    public int TR_VALDT = 0;
    public String APT_REF = " ";
    public double TOT_AMT = 0;
    public double TOT_PRIN = 0;
    public double TOT_INT = 0;
    public double TOT_PLC = 0;
    public double TOT_ILC = 0;
    public double HRG_AMT = 0;
    public char MLT_STL = ' ';
    public char WHD_RUL = ' ';
    public char CUR_TRM = ' ';
    public char CLN_CUT = ' ';
    public char SUBS_FLG = ' ';
    public LNCI4000_MULT_AC[] MULT_AC = new LNCI4000_MULT_AC[5];
    public LNCI4000_PART_INF[] PART_INF = new LNCI4000_PART_INF[10];
    public LNCI4000() {
        for (int i=0;i<5;i++) MULT_AC[i] = new LNCI4000_MULT_AC();
        for (int i=0;i<10;i++) PART_INF[i] = new LNCI4000_PART_INF();
    }
}
