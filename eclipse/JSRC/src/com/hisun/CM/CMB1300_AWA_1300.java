package com.hisun.CM;

public class CMB1300_AWA_1300 {
    public int IPT_DATE = 0;
    public short IPT_DATE_NO = 0;
    public String OTH_KEY = " ";
    public short OTH_KEY_NO = 0;
    public char STR_FLG = ' ';
    public short STR_FLG_NO = 0;
    public char END_FLG = ' ';
    public short END_FLG_NO = 0;
    public short EVE_CNT = 0;
    public short EVE_CNT_NO = 0;
    public CMB1300_EV_ARRAY[] EV_ARRAY = new CMB1300_EV_ARRAY[10];
    public CMB1300_AT_ARRAY[] AT_ARRAY = new CMB1300_AT_ARRAY[10];
    public CMB1300_AWA_1300() {
        for (int i=0;i<10;i++) EV_ARRAY[i] = new CMB1300_EV_ARRAY();
        for (int i=0;i<10;i++) AT_ARRAY[i] = new CMB1300_AT_ARRAY();
    }
}
