package com.hisun.DD;

public class DDB1560_AWA_1560 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public int OPEN_CNT = 0;
    public short OPEN_CNT_NO = 0;
    public String RMK = " ";
    public short RMK_NO = 0;
    public String CH_TLE = " ";
    public short CH_TLE_NO = 0;
    public String EN_TLE = " ";
    public short EN_TLE_NO = 0;
    public String EN_NAME = " ";
    public short EN_NAME_NO = 0;
    public char BAL_TYPE = ' ';
    public short BAL_TYPE_NO = 0;
    public int BAL_DATE = 0;
    public short BAL_DATE_NO = 0;
    public int BAL_EXPD = 0;
    public short BAL_EXPD_NO = 0;
    public int AC_CNT = 0;
    public short AC_CNT_NO = 0;
    public DDB1560_AC_LIST[] AC_LIST = new DDB1560_AC_LIST[6];
    public int BV_CNT = 0;
    public short BV_CNT_NO = 0;
    public DDB1560_BV_LIST[] BV_LIST = new DDB1560_BV_LIST[20];
    public DDB1560_AWA_1560() {
        for (int i=0;i<6;i++) AC_LIST[i] = new DDB1560_AC_LIST();
        for (int i=0;i<20;i++) BV_LIST[i] = new DDB1560_BV_LIST();
    }
}
