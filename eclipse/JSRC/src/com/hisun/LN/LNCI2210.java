package com.hisun.LN;

public class LNCI2210 {
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
    public double SUN_AMT = 0;
    public char MLT_STL = ' ';
    public char WHD_RUL = ' ';
    public char CUR_TRM = ' ';
    public char CLN_CUT = ' ';
    public char SUBS_FLG = ' ';
    public char PER_SYS = ' ';
    public double PY_O_INT = 0;
    public String MMO = " ";
    public LNCI2210_AC_ARRAY[] AC_ARRAY = new LNCI2210_AC_ARRAY[5];
    public LNCI2210_PARS_INF[] PARS_INF = new LNCI2210_PARS_INF[10];
    public LNCI2210() {
        for (int i=0;i<5;i++) AC_ARRAY[i] = new LNCI2210_AC_ARRAY();
        for (int i=0;i<10;i++) PARS_INF[i] = new LNCI2210_PARS_INF();
    }
}
