package com.hisun.LN;

public class LNZSHSQ_WS_HISTORY_DETAIL {
    char DEL_FLG = ' ';
    int TR_VALDT = 0;
    int TR_DTE = 0;
    long TR_JRNNO = 0;
    short TXN_TERM = 0;
    int DUE_DT = 0;
    double P_AMT = 0;
    double I_AMT = 0;
    double O_AMT = 0;
    double L_AMT = 0;
    double PC_AMT = 0;
    double F_AMT = 0;
    double OS_BAL = 0;
    double WO_AMT = 0;
    double WL_AMT = 0;
    double INT_RAT = 0;
    String REQ_FRM_NO = " ";
    char TXN_TYP = ' ';
    int TR_BR = 0;
    LNZSHSQ_WS_PAY_AC_INFO[] WS_PAY_AC_INFO = new LNZSHSQ_WS_PAY_AC_INFO[5];
    public LNZSHSQ_WS_HISTORY_DETAIL() {
        for (int i=0;i<5;i++) WS_PAY_AC_INFO[i] = new LNZSHSQ_WS_PAY_AC_INFO();
    }
}
