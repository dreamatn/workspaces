package com.hisun.CM;

public class CMZS0035_WS_OUTPUT {
    char WS_TR_TYPE = ' ';
    String WS_REQ_JRN = " ";
    int WS_REQ_DATE = 0;
    String WS_REQ_SYS = " ";
    String WS_REQ_CHNL = " ";
    String WS_TL_NO = " ";
    int WS_TR_BR = 0;
    String WS_AP_REF = " ";
    String WS_CHN_TYP = " ";
    CMZS0035_WS_AC_DATA[] WS_AC_DATA = new CMZS0035_WS_AC_DATA[2];
    CMZS0035_WS_MIB_DATA[] WS_MIB_DATA = new CMZS0035_WS_MIB_DATA[5];
    String WS_F_PRO_CD = " ";
    String WS_FIN_AC = " ";
    String WS_HLD_NO = " ";
    char WS_HLD_TYP = ' ';
    double WS_HLD_AMT = 0;
    int WS_VAL_DATE = 0;
    int WS_EXP_DATE = 0;
    String WS_HLD_RSN = " ";
    String WS_SMR = " ";
    char WS_PROC_STS = ' ';
    String WS_RET_CODE = " ";
    String WS_RET_MSG = " ";
    int WS_DATE = 0;
    long WS_JRNNO = 0;
    String WS_VCH_NO = " ";
    CMZS0035_WS_R_AC_DAT[] WS_R_AC_DAT = new CMZS0035_WS_R_AC_DAT[2];
    public CMZS0035_WS_OUTPUT() {
        for (int i=0;i<2;i++) WS_AC_DATA[i] = new CMZS0035_WS_AC_DATA();
        for (int i=0;i<5;i++) WS_MIB_DATA[i] = new CMZS0035_WS_MIB_DATA();
        for (int i=0;i<2;i++) WS_R_AC_DAT[i] = new CMZS0035_WS_R_AC_DAT();
    }
}
