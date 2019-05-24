package com.hisun.DD;

public class DDCI1560 {
    public char FUNC = ' ';
    public String CI_NO = " ";
    public int OPEN_CNT = 0;
    public String RMK = " ";
    public String CH_TLE = " ";
    public String EN_TLE = " ";
    public String EN_NAME = " ";
    public char BAL_TYPE = ' ';
    public int BAL_DATE = 0;
    public int BAL_EXPD = 0;
    public int AC_CNT = 0;
    public DDCI1560_AC_LIST[] AC_LIST = new DDCI1560_AC_LIST[6];
    public int BV_CNT = 0;
    public DDCI1560_BV_LIST[] BV_LIST = new DDCI1560_BV_LIST[20];
    public DDCI1560() {
        for (int i=0;i<6;i++) AC_LIST[i] = new DDCI1560_AC_LIST();
        for (int i=0;i<20;i++) BV_LIST[i] = new DDCI1560_BV_LIST();
    }
}
