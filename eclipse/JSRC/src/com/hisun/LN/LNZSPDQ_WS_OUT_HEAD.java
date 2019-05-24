package com.hisun.LN;

public class LNZSPDQ_WS_OUT_HEAD {
    short O_TOTAL_PAGE = 0;
    int O_TOTAL_NUM = 0;
    short O_CURR_PAGE = 0;
    char O_LAST_PAGE = ' ';
    short O_CURR_PAGE_ROW = 0;
    String O_CTA_NO = " ";
    char O_LN_STS = ' ';
    int O_BR = 0;
    String O_CI_NO = " ";
    String O_CI_CNM = " ";
    char FILLER52 = 0X02;
    String O_PROD_CD = " ";
    String O_PD_DESC = " ";
    char FILLER55 = 0X02;
    String O_CCY = " ";
    double O_LON_PRIN = 0;
    double O_LON_BAL = 0;
    double O_P_P_BAL = 0;
    double O_O_P_BAL = 0;
    double O_CUR_RAT = 0;
    char FILLER62 = 0X01;
    double O_CUR_PO_RAT = 0;
    char FILLER64 = 0X01;
    double O_D_I_AMT = 0;
    int O_TR_VAL_DATE = 0;
    double O_TOT_AMT = 0;
    double O_TOT_P_AMT = 0;
    double O_TOT_I_AMT = 0;
    double O_TOT_O_AMT = 0;
    double O_TOT_L_AMT = 0;
    int O_P_O_DT = 0;
    char FILLER73 = 0X01;
    int O_I_L_DT = 0;
    char FILLER75 = 0X01;
    double O_TAC_MGT_AMT = 0;
    double O_THRG_AMT = 0;
    double O_T_I_AMT = 0;
    double O_T_O_AMT = 0;
    double O_T_L_AMT = 0;
    LNZSPDQ_WS_O_PARTI_INFO WS_O_PARTI_INFO = new LNZSPDQ_WS_O_PARTI_INFO();
    LNZSPDQ_WS_O_AC_INFO[] WS_O_AC_INFO = new LNZSPDQ_WS_O_AC_INFO[5];
    public LNZSPDQ_WS_OUT_HEAD() {
        for (int i=0;i<5;i++) WS_O_AC_INFO[i] = new LNZSPDQ_WS_O_AC_INFO();
    }
}
