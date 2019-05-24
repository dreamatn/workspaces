package com.hisun.PN;

public class MPRDN_PROD_TL {
    int AC_NUM = 0;
    int AS_NUM = 0;
    char CI_TYP = ' ';
    MPRDN_PROD_DD_INFO[] PROD_DD_INFO = new MPRDN_PROD_DD_INFO[10];
    MPRDN_PROD_TD_INFO[] PROD_TD_INFO = new MPRDN_PROD_TD_INFO[10];
    String CCY = " ";
    char CCY_TYPE = ' ';
    short DAYS = 0;
    short PERD = 0;
    char PERD_UNIT = ' ';
    char REPY_MTH = ' ';
    char PERM_KND = ' ';
    short MMDD = 0;
    public MPRDN_PROD_TL() {
        for (int i=0;i<10;i++) PROD_DD_INFO[i] = new MPRDN_PROD_DD_INFO();
        for (int i=0;i<10;i++) PROD_TD_INFO[i] = new MPRDN_PROD_TD_INFO();
    }
}
