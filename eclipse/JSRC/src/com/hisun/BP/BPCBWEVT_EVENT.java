package com.hisun.BP;

public class BPCBWEVT_EVENT {
    public short CPN_CALL_SEQ = 0;
    public String MOD_NO = " ";
    public String CNTR_TYPE = " ";
    public String PROD_CODE = " ";
    public String AC_NO = " ";
    public String EVENT_CODE = " ";
    public String CNTR_TYPE_REL = " ";
    public String PROD_CODE_REL = " ";
    public String AC_NO_REL = " ";
    public String EVENT_CODE_REL = " ";
    public int BR_OLD = 0;
    public int BR_NEW = 0;
    public BPCBWEVT_EVENT_CCY[] EVENT_CCY = new BPCBWEVT_EVENT_CCY[5];
    public int VAL_DATE = 0;
    public String DD_AC = " ";
    public String IB_GL = " ";
    public BPCBWEVT_GL_AC[] GL_AC = new BPCBWEVT_GL_AC[10];
    public BPCBWEVT_EVENT_AMT[] EVENT_AMT = new BPCBWEVT_EVENT_AMT[76];
    public String CI_NO = " ";
    public String REF_NO = " ";
    public String PORTFO_CD = " ";
    public String CHQ_NO = " ";
    public int POST_DATE = 0;
    public String POST_NARR = " ";
    public String NARR_CD = " ";
    public String DESC = " ";
    public String RVS_NO = " ";
    public int RVS_SEQ = 0;
    public char AC_FLG = ' ';
    public int EFF_DAYS = 0;
    public String PAY_MAN = " ";
    public int PAY_BR = 0;
    public String THEIR_AC = " ";
    public String THEIR_CCY = " ";
    public double THEIR_AMT = 0;
    public double THEIR_RAT = 0;
    public int SETTLE_DT = 0;
    public String FLR = " ";
    public BPCBWEVT_EVENT() {
        for (int i=0;i<5;i++) EVENT_CCY[i] = new BPCBWEVT_EVENT_CCY();
        for (int i=0;i<10;i++) GL_AC[i] = new BPCBWEVT_GL_AC();
        for (int i=0;i<76;i++) EVENT_AMT[i] = new BPCBWEVT_EVENT_AMT();
    }
}
