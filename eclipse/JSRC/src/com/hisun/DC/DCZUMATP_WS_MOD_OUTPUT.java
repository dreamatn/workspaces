package com.hisun.DC;

public class DCZUMATP_WS_MOD_OUTPUT {
    String WS_MOD_OVR_NO = " ";
    String WS_MOD_AC_NO = " ";
    String WS_MOD_CI_NAME = " ";
    char DCZUMATP_FILLER115 = 0X02;
    String WS_MOD_PROD_CODE = " ";
    short WS_MOD_PROD_LVL = 0;
    String WS_MOD_CCY = " ";
    char WS_MOD_CCY_TYPE = ' ';
    int WS_MOD_PROCS_DATE = 0;
    int WS_MOD_PROCL_DATE = 0;
    char WS_MOD_PROC_STS = ' ';
    char WS_MOD_PROC_TYP = ' ';
    short WS_MOD_TRIG_MD = 0;
    char WS_MOD_PERM_KND = ' ';
    char WS_MOD_TRIG_TMS = ' ';
    double WS_MOD_TRM_AMT = 0;
    char DCZUMATP_FILLER128 = 0X01;
    char WS_MOD_TRIG_MTH = ' ';
    char WS_MOD_INT_MTH = ' ';
    double WS_MOD_MRM_AMT = 0;
    char DCZUMATP_FILLER132 = 0X01;
    double WS_MOD_TRC_AMT = 0;
    char DCZUMATP_FILLER134 = 0X01;
    double WS_MOD_TRPCT_S = 0;
    char DCZUMATP_FILLER136 = 0X01;
    String WS_MOD_TRC_TDAC = " ";
    String WS_MOD_TERM = " ";
    char WS_MOD_DRAW_FLG = ' ';
    double WS_MOD_DRAW_MAX = 0;
    char DCZUMATP_FILLER141 = 0X01;
    double WS_MOD_DRAW_MIN = 0;
    char DCZUMATP_FILLER143 = 0X01;
    double WS_MOD_DRAW_AMT = 0;
    char DCZUMATP_FILLER145 = 0X01;
    double WS_MOD_LIMT_AMT = 0;
    char DCZUMATP_FILLER147 = 0X01;
    DCZUMATP_WS_MOD_LNK_INFO[] WS_MOD_LNK_INFO = new DCZUMATP_WS_MOD_LNK_INFO[5];
    public DCZUMATP_WS_MOD_OUTPUT() {
        for (int i=0;i<5;i++) WS_MOD_LNK_INFO[i] = new DCZUMATP_WS_MOD_LNK_INFO();
    }
}
