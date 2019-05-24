package com.hisun.LN;

public class LNZSPREQ_WS_OUT_DATA {
    String WS_O_CTA = " ";
    int WS_O_BR = 0;
    String WS_O_CI_NO = " ";
    String WS_O_CI_CNM = " ";
    char LNZSPREQ_FILLER32 = 0X02;
    String WS_O_PROD_CD = " ";
    String WS_O_PROD_DE = " ";
    char LNZSPREQ_FILLER35 = 0X02;
    String WS_O_CCY = " ";
    double WS_O_PRINCIPAL = 0;
    double WS_O_BALANCE = 0;
    int WS_O_TR_VAL_DTE = 0;
    double WS_O_TOT_P_AMT = 0;
    char WS_O_INT_MODE = ' ';
    double WS_O_TOT_I_AMT = 0;
    double WS_O_PC_RATE = 0;
    char LNZSPREQ_FILLER44 = 0X01;
    double WS_O_PC_AMT = 0;
    double WS_O_HRG_AMT = 0;
    double WS_O_PROJ_INT = 0;
    double WS_O_TOT_AMT = 0;
    double WS_O_T_I_AMT = 0;
    double WS_O_T_O_AMT = 0;
    double WS_O_T_L_AMT = 0;
    String WS_O_AC_TYP1 = " ";
    String WS_O_PAY_AC1 = " ";
    double WS_O_PAY_AC_BAL1 = 0;
    String WS_O_AC_TYP2 = " ";
    String WS_O_PAY_AC2 = " ";
    double WS_O_PAY_AC_BAL2 = 0;
    String WS_O_AC_TYP3 = " ";
    String WS_O_PAY_AC3 = " ";
    double WS_O_PAY_AC_BAL3 = 0;
    String WS_O_AC_TYP4 = " ";
    String WS_O_PAY_AC4 = " ";
    double WS_O_PAY_AC_BAL4 = 0;
    String WS_O_AC_TYP5 = " ";
    String WS_O_PAY_AC5 = " ";
    double WS_O_PAY_AC_BAL5 = 0;
    LNZSPREQ_WS_O_JOIN_INFO[] WS_O_JOIN_INFO = new LNZSPREQ_WS_O_JOIN_INFO[10];
    public LNZSPREQ_WS_OUT_DATA() {
        for (int i=0;i<10;i++) WS_O_JOIN_INFO[i] = new LNZSPREQ_WS_O_JOIN_INFO();
    }
}
