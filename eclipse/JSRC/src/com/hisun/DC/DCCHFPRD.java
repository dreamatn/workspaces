package com.hisun.DC;

public class DCCHFPRD {
    public String CON_MDEL = " ";
    public String PROD_CODE = " ";
    public String PROD_DESC = " ";
    public String EFFDAT = " ";
    public String EXPDAT = " ";
    public char CI_TYP = ' ';
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public short PERM_MMDD = 0;
    public char OVR_CARD_FLG = ' ';
    public DCCHFPRD_PROD_DD_INFO[] PROD_DD_INFO = new DCCHFPRD_PROD_DD_INFO[10];
    public DCCHFPRD_PROD_TD_INFO[] PROD_TD_INFO = new DCCHFPRD_PROD_TD_INFO[10];
    public char DD_TRS_FLG = ' ';
    public DCCHFPRD_USAGE_INFO[] USAGE_INFO = new DCCHFPRD_USAGE_INFO[10];
    public char DRAW_MTH = ' ';
    public char DRAW_ORDER = ' ';
    public char INOUT_FG = ' ';
    public short OUT_LVL = 0;
    public short IN_LVL = 0;
    public char OVR_BANK_FLG = ' ';
    public char TRIG_TMS = ' ';
    public DCCHFPRD_PERM_INFO[] PERM_INFO = new DCCHFPRD_PERM_INFO[5];
    public DCCHFPRD_INT_INFO[] INT_INFO = new DCCHFPRD_INT_INFO[5];
    public DCCHFPRD() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new DCCHFPRD_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new DCCHFPRD_PROD_TD_INFO();
        for (int i=0;i<10;i++) USAGE_INFO[i] = new DCCHFPRD_USAGE_INFO();
        for (int i=0;i<5;i++) PERM_INFO[i] = new DCCHFPRD_PERM_INFO();
        for (int i=0;i<5;i++) INT_INFO[i] = new DCCHFPRD_INT_INFO();
    }
}
