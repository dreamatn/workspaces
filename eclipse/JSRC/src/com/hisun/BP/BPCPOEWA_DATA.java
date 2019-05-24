package com.hisun.BP;

public class BPCPOEWA_DATA {
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
    public int BR_3 = 0;
    public int BR_4 = 0;
    public int BR_5 = 0;
    public BPCPOEWA_CCY_INFO[] CCY_INFO = new BPCPOEWA_CCY_INFO[5];
    public int VALUE_DATE = 0;
    public String DD_AC = " ";
    public String IB_GL = " ";
    public BPCPOEWA_GLAC_INFO[] GLAC_INFO = new BPCPOEWA_GLAC_INFO[10];
    public BPCPOEWA_AMT_INFO[] AMT_INFO = new BPCPOEWA_AMT_INFO[76];
    public String CI_NO = " ";
    public String REF_NO = " ";
    public String PORT = " ";
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
    public String OTH_MAKER = " ";
    public String OTH_CHECKER = " ";
    public String OTH = " ";
    public String FILLER = " ";
    public String RES_CENT = " ";
    public String LINE = " ";
    public String INT_DEALINGS = " ";
    public char RESERVE = ' ';
    public BPCPOEWA_DATA() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPCPOEWA_CCY_INFO();
        for (int i=0;i<10;i++) GLAC_INFO[i] = new BPCPOEWA_GLAC_INFO();
        for (int i=0;i<76;i++) AMT_INFO[i] = new BPCPOEWA_AMT_INFO();
    }
}
