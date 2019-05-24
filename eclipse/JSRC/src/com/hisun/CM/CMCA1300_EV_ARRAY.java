package com.hisun.CM;

public class CMCA1300_EV_ARRAY {
    public String PRDMO_CD = " ";
    public String EVENT_CD = " ";
    public String PRO_OLD = " ";
    public String PRO_NEW = " ";
    public String CAT_OLD = " ";
    public String CAT_NEW = " ";
    public int VAL_DT = 0;
    public String CCY = " ";
    public int BR_OLD = 0;
    public int BR_NEW = 0;
    public int BR_3 = 0;
    public int BR_4 = 0;
    public int BR_5 = 0;
    public String AC_NO = " ";
    public String CI_NO = " ";
    public char RED_FLG = ' ';
    public String RVS_NO = " ";
    public String DESC = " ";
    public String RMK = " ";
    public CMCA1300_AT_ARRAY[] AT_ARRAY = new CMCA1300_AT_ARRAY[10];
    public CMCA1300_EV_ARRAY() {
        for (int i=0;i<10;i++) AT_ARRAY[i] = new CMCA1300_AT_ARRAY();
    }
}
