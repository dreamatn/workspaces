package com.hisun.DC;

public class DCCAMPRD_PROD_TL {
    public int AC_NUM = 0;
    public int AS_NUM = 0;
    public char CI_TYP = ' ';
    public DCCAMPRD_PROD_DD_INFO[] PROD_DD_INFO = new DCCAMPRD_PROD_DD_INFO[10];
    public DCCAMPRD_PROD_TD_INFO[] PROD_TD_INFO = new DCCAMPRD_PROD_TD_INFO[10];
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public short DAYS = 0;
    public short PERD = 0;
    public char PERD_UNIT = ' ';
    public char REPY_MTH = ' ';
    public char PERM_KND = ' ';
    public short MMDD = 0;
    public DCCAMPRD_PROD_TL() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new DCCAMPRD_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new DCCAMPRD_PROD_TD_INFO();
    }
}
