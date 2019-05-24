package com.hisun.BP;

public class BPZUGLM_WS_VAL {
    String WS_BK = " ";
    int WS_GL_MSTNO = 0;
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    String WS_COA_FLG = " ";
    String WS_SNAME = " ";
    String WS_LNAME = " ";
    char WS_OPT_FLG = ' ';
    String WS_CNTY1 = " ";
    String WS_CNTY2 = " ";
    String WS_CNTY3 = " ";
    char WS_CKFLG = ' ';
    String WS_REAL_GL = " ";
    String WS_MEMO_GL = " ";
    BPZUGLM_WS_REL_ITMS[] WS_REL_ITMS = new BPZUGLM_WS_REL_ITMS[60];
    String WS_UPD_TLR = " ";
    int WS_UPD_DATE = 0;
    int WS_UPD_TIME = 0;
    String WS_SUP_TEL1 = " ";
    String WS_SUP_TEL2 = " ";
    public BPZUGLM_WS_VAL() {
        for (int i=0;i<60;i++) WS_REL_ITMS[i] = new BPZUGLM_WS_REL_ITMS();
    }
}
