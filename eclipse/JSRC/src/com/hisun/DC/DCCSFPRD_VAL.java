package com.hisun.DC;

public class DCCSFPRD_VAL {
    public String PROD_DESC = " ";
    public int EFFDAT = 0;
    public int EXPDAT = 0;
    public char CI_TYP = ' ';
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public char OVR_CARD_FLG = ' ';
    public DCCSFPRD_PROD_DD_INFO[] PROD_DD_INFO = new DCCSFPRD_PROD_DD_INFO[10];
    public DCCSFPRD_PROD_TD_INFO[] PROD_TD_INFO = new DCCSFPRD_PROD_TD_INFO[10];
    public String USAGE_MTH = " ";
    public char DRAW_MTH = ' ';
    public char DRAW_ORDER = ' ';
    public DCCSFPRD_INT_INFO[] INT_INFO = new DCCSFPRD_INT_INFO[5];
    public DCCSFPRD_PERM_INFO[] PERM_INFO = new DCCSFPRD_PERM_INFO[5];
    public char INOUT_FG = ' ';
    public short OUT_LVL = 0;
    public short IN_LVL = 0;
    public char TRIG_TMS = ' ';
    public char OVR_BANK_FLG = ' ';
    public DCCSFPRD_VAL() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new DCCSFPRD_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new DCCSFPRD_PROD_TD_INFO();
        for (int i=0;i<5;i++) INT_INFO[i] = new DCCSFPRD_INT_INFO();
        for (int i=0;i<5;i++) PERM_INFO[i] = new DCCSFPRD_PERM_INFO();
    }
}
