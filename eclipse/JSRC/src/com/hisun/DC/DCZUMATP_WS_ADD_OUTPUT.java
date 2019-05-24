package com.hisun.DC;

public class DCZUMATP_WS_ADD_OUTPUT {
    String WS_ADD_OVR_NO = " ";
    String WS_ADD_AC_NO = " ";
    String WS_ADD_CI_NAME = " ";
    char DCZUMATP_FILLER31 = 0X02;
    String WS_ADD_PROD_CODE = " ";
    short WS_ADD_PROD_LVL = 0;
    String WS_ADD_CCY = " ";
    char WS_ADD_CCY_TYPE = ' ';
    int WS_ADD_PROCS_DATE = 0;
    int WS_ADD_PROCL_DATE = 0;
    char WS_ADD_PROC_STS = ' ';
    char WS_ADD_PROC_TYP = ' ';
    char WS_ADD_PERM_KND = ' ';
    char WS_ADD_TRIG_TMS = ' ';
    double WS_ADD_TRM_AMT = 0;
    char DCZUMATP_FILLER43 = 0X01;
    char WS_ADD_TRIG_MTH = ' ';
    char WS_ADD_INT_MTH = ' ';
    double WS_ADD_MRM_AMT = 0;
    char DCZUMATP_FILLER47 = 0X01;
    double WS_ADD_TRC_AMT = 0;
    char DCZUMATP_FILLER49 = 0X01;
    double WS_ADD_TRPCT_S = 0;
    char DCZUMATP_FILLER51 = 0X01;
    String WS_ADD_TR_AGRNO = " ";
    String WS_ADD_TERM = " ";
    char WS_ADD_DRAW_FLG = ' ';
    double WS_ADD_DRAW_MAX = 0;
    char DCZUMATP_FILLER56 = 0X01;
    double WS_ADD_DRAW_MIN = 0;
    char DCZUMATP_FILLER58 = 0X01;
    double WS_ADD_DRAW_AMT = 0;
    char DCZUMATP_FILLER60 = 0X01;
    double WS_ADD_LIMT_AMT = 0;
    char DCZUMATP_FILLER62 = 0X01;
    DCZUMATP_WS_ADD_LNK_INFO[] WS_ADD_LNK_INFO = new DCZUMATP_WS_ADD_LNK_INFO[5];
    public DCZUMATP_WS_ADD_OUTPUT() {
        for (int i=0;i<5;i++) WS_ADD_LNK_INFO[i] = new DCZUMATP_WS_ADD_LNK_INFO();
    }
}
