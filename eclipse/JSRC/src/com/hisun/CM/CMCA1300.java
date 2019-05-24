package com.hisun.CM;

public class CMCA1300 {
    public int IPT_DATE = 0;
    public String OTH_KEY = " ";
    public char STR_FLG = ' ';
    public char END_FLG = ' ';
    public short EVE_CNT = 0;
    public CMCA1300_EV_ARRAY[] EV_ARRAY = new CMCA1300_EV_ARRAY[10];
    public CMCA1300() {
        for (int i=0;i<10;i++) EV_ARRAY[i] = new CMCA1300_EV_ARRAY();
    }
}
