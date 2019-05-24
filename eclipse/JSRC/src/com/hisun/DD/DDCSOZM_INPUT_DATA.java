package com.hisun.DD;

public class DDCSOZM_INPUT_DATA {
    public char IZM_FUNC = ' ';
    public String IZM_CI_NO = " ";
    public int IZM_OPEN_CNT = 0;
    public String IZM_RMK = " ";
    public String IZM_CH_TLE = " ";
    public String IZM_EN_TLE = " ";
    public String IZM_EN_NAME = " ";
    public char IZM_BAL_TYPE = ' ';
    public int IZM_BAL_DATE = 0;
    public int IZM_BAL_EXPD = 0;
    public int IZM_AC_CNT = 0;
    public DDCSOZM_IZM_AC_LIST[] IZM_AC_LIST = new DDCSOZM_IZM_AC_LIST[6];
    public int IZM_BV_CNT = 0;
    public DDCSOZM_IZM_BV_LIST[] IZM_BV_LIST = new DDCSOZM_IZM_BV_LIST[99];
    public DDCSOZM_INPUT_DATA() {
        for (int i=0;i<6;i++) IZM_AC_LIST[i] = new DDCSOZM_IZM_AC_LIST();
        for (int i=0;i<99;i++) IZM_BV_LIST[i] = new DDCSOZM_IZM_BV_LIST();
    }
}
