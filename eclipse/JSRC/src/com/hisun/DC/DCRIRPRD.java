package com.hisun.DC;

public class DCRIRPRD {
    public DCRIRPRD_KEY KEY = new DCRIRPRD_KEY();
    public String PROD_DESC = " ";
    public String EFFDAT = " ";
    public String EXPDAT = " ";
    public char CI_TYP = ' ';
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public char OVR_CARD_FLG = ' ';
    public DCRIRPRD_PROD_DD_INFO[] PROD_DD_INFO = new DCRIRPRD_PROD_DD_INFO[10];
    public DCRIRPRD_PROD_TD_INFO[] PROD_TD_INFO = new DCRIRPRD_PROD_TD_INFO[10];
    public String USAGE_MTH = " ";
    public char DRAW_MTH = ' ';
    public char DRAW_ORDER = ' ';
    public char INOUT_FG = ' ';
    public short OUT_LVL = 0;
    public short IN_LVL = 0;
    public char OVR_BANK = ' ';
    public char TRIG_TMS = ' ';
    public DCRIRPRD_PERM_INFO[] PERM_INFO = new DCRIRPRD_PERM_INFO[5];
    public DCRIRPRD_PERM_INFO1[] PERM_INFO1 = new DCRIRPRD_PERM_INFO1[5];
    public DCRIRPRD_INT_INFO[] INT_INFO = new DCRIRPRD_INT_INFO[5];
    public DCRIRPRD() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new DCRIRPRD_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new DCRIRPRD_PROD_TD_INFO();
        for (int i=0;i<5;i++) PERM_INFO[i] = new DCRIRPRD_PERM_INFO();
        for (int i=0;i<5;i++) PERM_INFO1[i] = new DCRIRPRD_PERM_INFO1();
        for (int i=0;i<5;i++) INT_INFO[i] = new DCRIRPRD_INT_INFO();
    }
}
