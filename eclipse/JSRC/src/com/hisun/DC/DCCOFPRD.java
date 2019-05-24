package com.hisun.DC;

public class DCCOFPRD {
    public String PROD_CODE = " ";
    public String PROD_DESC = " ";
    public char FILLER3 = 0X02;
    public String EFFDAT = " ";
    public String EXPDAT = " ";
    public char CI_TYP = ' ';
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public char OVR_BANK = ' ';
    public char OVR_CARD_FLG = ' ';
    public DCCOFPRD_PROD_DD_INFO[] PROD_DD_INFO = new DCCOFPRD_PROD_DD_INFO[10];
    public DCCOFPRD_PROD_TD_INFO[] PROD_TD_INFO = new DCCOFPRD_PROD_TD_INFO[10];
    public String USAGE_MTH = " ";
    public char DRAW_MTH = ' ';
    public char DRAW_ORDER = ' ';
    public char INOUT_FG = ' ';
    public short OUT_LVL = 0;
    public short IN_LVL = 0;
    public char TRIG_TMS = ' ';
    public DCCOFPRD_PERM_INFO[] PERM_INFO = new DCCOFPRD_PERM_INFO[5];
    public DCCOFPRD_PERM_INFO1[] PERM_INFO1 = new DCCOFPRD_PERM_INFO1[5];
    public DCCOFPRD_INT_INFO[] INT_INFO = new DCCOFPRD_INT_INFO[5];
    public int CRT_DATE = 0;
    public String CRT_TLR = " ";
    public int UPDTBL_DATE = 0;
    public String UPDTBL_TLR = " ";
    public DCCOFPRD() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new DCCOFPRD_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new DCCOFPRD_PROD_TD_INFO();
        for (int i=0;i<5;i++) PERM_INFO[i] = new DCCOFPRD_PERM_INFO();
        for (int i=0;i<5;i++) PERM_INFO1[i] = new DCCOFPRD_PERM_INFO1();
        for (int i=0;i<5;i++) INT_INFO[i] = new DCCOFPRD_INT_INFO();
    }
}
