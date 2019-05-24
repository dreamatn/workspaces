package com.hisun.DC;

public class DCCUMPRM_DATA {
    public DCCUMPRM_KEY KEY = new DCCUMPRM_KEY();
    public String PROD_DESC = " ";
    public int EFFDAT = 0;
    public int EXPDAT = 0;
    public char CI_TYP = ' ';
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public char OVR_CARD_FLG = ' ';
    public DCCUMPRM_PROD_DD_INFO[] PROD_DD_INFO = new DCCUMPRM_PROD_DD_INFO[10];
    public DCCUMPRM_PROD_TD_INFO[] PROD_TD_INFO = new DCCUMPRM_PROD_TD_INFO[10];
    public String USAGE_MTH = " ";
    public char DRAW_MTH = ' ';
    public char DRAW_ORDER = ' ';
    public char INOUT_FG = ' ';
    public short OUT_LVL = 0;
    public short IN_LVL = 0;
    public char OVR_BANK = ' ';
    public char TRIG_TMS = ' ';
    public DCCUMPRM_PERM_INFO[] PERM_INFO = new DCCUMPRM_PERM_INFO[5];
    public DCCUMPRM_PERM_INFO1[] PERM_INFO1 = new DCCUMPRM_PERM_INFO1[5];
    public DCCUMPRM_INT_INFO[] INT_INFO = new DCCUMPRM_INT_INFO[5];
    public DCCUMPRM_DATA() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new DCCUMPRM_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new DCCUMPRM_PROD_TD_INFO();
        for (int i=0;i<5;i++) PERM_INFO[i] = new DCCUMPRM_PERM_INFO();
        for (int i=0;i<5;i++) PERM_INFO1[i] = new DCCUMPRM_PERM_INFO1();
        for (int i=0;i<5;i++) INT_INFO[i] = new DCCUMPRM_INT_INFO();
    }
}
