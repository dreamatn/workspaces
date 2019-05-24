package com.hisun.BP;

public class BPZSGLM_WS_VAL {
    String WS_COA_FLG = " ";
    String WS_SNAME = " ";
    String WS_LNAME = " ";
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    char WS_OPT_FLG = ' ';
    String WS_CNTY1 = " ";
    String WS_CNTY2 = " ";
    String WS_CNTY3 = " ";
    char WS_CKFLG = ' ';
    String WS_REAL_GL = " ";
    String WS_MEMO_GL = " ";
    int WS_QBKPM_CNT = 0;
    BPZSGLM_WS_REL_ITMS[] WS_REL_ITMS = new BPZSGLM_WS_REL_ITMS[60];
    public BPZSGLM_WS_VAL() {
        for (int i=0;i<60;i++) WS_REL_ITMS[i] = new BPZSGLM_WS_REL_ITMS();
    }
}
