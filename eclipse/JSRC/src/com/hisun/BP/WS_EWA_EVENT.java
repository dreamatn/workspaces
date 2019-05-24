package com.hisun.WS;

public class WS_EWA_EVENT {
    short EWA_CPN_CALL_SEQ = 0;
    String EWA_MOD_NO = " ";
    String EWA_CNTR_TYPE = " ";
    String EWA_PROD_CODE = " ";
    String EWA_AC_NO = " ";
    String EWA_EVENT_CODE = " ";
    String EWA_CNTR_TYPE_REL = " ";
    String EWA_PROD_CODE_REL = " ";
    String EWA_AC_NO_REL = " ";
    String EWA_EVENT_CODE_REL = " ";
    int EWA_BR_OLD = 0;
    int EWA_BR_NEW = 0;
    int EWA_BR_3 = 0;
    int EWA_BR_4 = 0;
    int EWA_BR_5 = 0;
    WS_EWA_EVENT_CCY[] EWA_EVENT_CCY = new WS_EWA_EVENT_CCY[5];
    int EWA_VAL_DATE = 0;
    String EWA_DD_AC = " ";
    String EWA_IB_GL = " ";
    WS_EWA_GL_AC[] EWA_GL_AC = new WS_EWA_GL_AC[10];
    WS_EWA_EVENT_AMT[] EWA_EVENT_AMT = new WS_EWA_EVENT_AMT[76];
    String EWA_CI_NO = " ";
    String EWA_REF_NO = " ";
    String EWA_PORTFO_CD = " ";
    String EWA_CHQ_NO = " ";
    int EWA_POST_DATE = 0;
    String EWA_POST_NARR = " ";
    String EWA_NARR_CD = " ";
    String EWA_DESC = " ";
    String EWA_RVS_NO = " ";
    int EWA_RVS_SEQ = 0;
    char EWA_AC_FLG = ' ';
    int EWA_EFF_DAYS = 0;
    String EWA_PAY_MAN = " ";
    int EWA_PAY_BR = 0;
    String EWA_THEIR_AC = " ";
    String EWA_THEIR_CCY = " ";
    double EWA_THEIR_AMT = 0;
    double EWA_THEIR_RAT = 0;
    int EWA_SETTLE_DT = 0;
    String EWA_OTH_MAKER = " ";
    String EWA_OTH_CHECKER = " ";
    String EWA_OTH = " ";
    String EWA_FLR = " ";
    String EWA_RES_CENT = " ";
    String EWA_LINE = " ";
    String EWA_INT_DEALINGS = " ";
    char EWA_RESERVE = ' ';
    public WS_EWA_EVENT() {
        for (int i=0;i<5;i++) EWA_EVENT_CCY[i] = new WS_EWA_EVENT_CCY();
        for (int i=0;i<10;i++) EWA_GL_AC[i] = new WS_EWA_GL_AC();
        for (int i=0;i<76;i++) EWA_EVENT_AMT[i] = new WS_EWA_EVENT_AMT();
    }
}
