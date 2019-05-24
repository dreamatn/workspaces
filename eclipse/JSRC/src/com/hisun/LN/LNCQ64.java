package com.hisun.LN;

public class LNCQ64 {
    public String CTA = " ";
    public int BR = 0;
    public String CI_NO = " ";
    public String CI_CNM = " ";
    public char FILLER5 = 0X02;
    public String PROD_CD = " ";
    public String PROD_DE = " ";
    public char FILLER8 = 0X02;
    public String CCY = " ";
    public double PRINCIPAL = 0;
    public double BALANCE = 0;
    public int TR_VAL_DTE = 0;
    public double TOT_P_AMT = 0;
    public char INT_MODE = ' ';
    public double TOT_I_AMT = 0;
    public double PC_RATE = 0;
    public char FILLER17 = 0X01;
    public double PC_AMT = 0;
    public double HRG_AMT = 0;
    public double SUN_BF = 0;
    public double PROJ_INT = 0;
    public double TOT_AMT = 0;
    public double T_I_AMT = 0;
    public double T_O_AMT = 0;
    public double T_L_AMT = 0;
    public String AC_TYP1 = " ";
    public String PAY_AC1 = " ";
    public double PAY_AC_BAL1 = 0;
    public String AC_TYP2 = " ";
    public String PAY_AC2 = " ";
    public double PAY_AC_BAL2 = 0;
    public String AC_TYP3 = " ";
    public String PAY_AC3 = " ";
    public double PAY_AC_BAL3 = 0;
    public String AC_TYP4 = " ";
    public String PAY_AC4 = " ";
    public double PAY_AC_BAL4 = 0;
    public String AC_TYP5 = " ";
    public String PAY_AC5 = " ";
    public double PAY_AC_BAL5 = 0;
    public LNCQ64_JOIN_INFO[] JOIN_INFO = new LNCQ64_JOIN_INFO[10];
    public LNCQ64() {
        for (int i=0;i<10;i++) JOIN_INFO[i] = new LNCQ64_JOIN_INFO();
    }
}
