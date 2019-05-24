package com.hisun.DC;

public class DCZUMATP_WS_DETAIL_OUTPUT {
    String WS_DTL_AGR_NO = " ";
    String WS_DTL_OVR_NO = " ";
    String WS_DTL_CI_NAME = " ";
    char DCZUMATP_FILLER69 = 0X02;
    String WS_DTL_PROD_CODE = " ";
    short WS_DTL_PROD_LVL = 0;
    String WS_DTL_CCY = " ";
    char WS_DTL_CCY_TYPE = ' ';
    int WS_DTL_PROCS_DATE = 0;
    int WS_DTL_PROCL_DATE = 0;
    char WS_DTL_PROC_STS = ' ';
    char WS_DTL_PROC_TYP = ' ';
    char WS_DTL_PERM_KND = ' ';
    short WS_DTL_TRIG_MD = 0;
    short WS_DTL_PERD = 0;
    char WS_DTL_TRIG_TMS = ' ';
    double WS_DTL_TRM_AMT = 0;
    char DCZUMATP_FILLER83 = 0X01;
    char WS_DTL_TRIG_MTH = ' ';
    char WS_DTL_INT_MTH = ' ';
    double WS_DTL_MRM_AMT = 0;
    char DCZUMATP_FILLER90 = 0X01;
    double WS_DTL_TRC_AMT = 0;
    char DCZUMATP_FILLER92 = 0X01;
    double WS_DTL_TRPCT_S = 0;
    char DCZUMATP_FILLER94 = 0X01;
    String WS_DTL_TRC_TDAC = " ";
    String WS_DTL_TERM = " ";
    int WS_DTL_NEXT_DT = 0;
    int WS_CRT_DATE = 0;
    char WS_DTL_DRAW_FLG = ' ';
    String WS_DTL_DRAW_USE = " ";
    double WS_DTL_DRAW_MAX = 0;
    char DCZUMATP_FILLER102 = 0X01;
    double WS_DTL_DRAW_MIN = 0;
    char DCZUMATP_FILLER104 = 0X01;
    double WS_DTL_DRAW_AMT = 0;
    char DCZUMATP_FILLER106 = 0X01;
    double WS_DTL_LIMT_AMT = 0;
    char DCZUMATP_FILLER108 = 0X01;
    DCZUMATP_WS_DTL_LNK_INFO[] WS_DTL_LNK_INFO = new DCZUMATP_WS_DTL_LNK_INFO[5];
    public DCZUMATP_WS_DETAIL_OUTPUT() {
        for (int i=0;i<5;i++) WS_DTL_LNK_INFO[i] = new DCZUMATP_WS_DTL_LNK_INFO();
    }
}
