package com.hisun.LN;

public class LNZSPDQ_WS_VARIABLES {
    short I = 0;
    short J = 0;
    int DATE = 0;
    int DAYS = 0;
    int PIA_FST_DT = 0;
    int PIA_LST_DT = 0;
    int IIA_FST_DT = 0;
    int IIA_LST_DT = 0;
    double LON_ACRINT = 0;
    String CTL_STSW = " ";
    double CUR_RAT = 0;
    double CUR_PO_RAT = 0;
    short TOTAL_PAGE = 0;
    int TOTAL_NUM = 0;
    short CURR_PAGE = 0;
    char LAST_PAGE = ' ';
    short PAGE_ROW = 0;
    int NEXT_START_NUM = 0;
    int BAL_CNT = 0;
    short IDXA = 0;
    double P_REC_AMT = 0;
    double I_REC_AMT = 0;
    double T_REC_AMT = 0;
    LNZSPDQ_WS_AC_INFO[] WS_AC_INFO = new LNZSPDQ_WS_AC_INFO[5];
    char PARS_FND_FLG = ' ';
    double TOT_P_AMT_N = 0;
    double TOT_I_AMT_N = 0;
    double TOT_O_AMT_N = 0;
    double TOT_L_AMT_N = 0;
    public LNZSPDQ_WS_VARIABLES() {
        for (int i=0;i<5;i++) WS_AC_INFO[i] = new LNZSPDQ_WS_AC_INFO();
    }
}
