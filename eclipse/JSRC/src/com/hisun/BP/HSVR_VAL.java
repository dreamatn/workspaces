package com.hisun.BP;

public class HSVR_VAL {
    HSVR_DATA[] DATA = new HSVR_DATA[20];
    int EFF_DATE = 0;
    int EXP_DATE = 0;
    char AUT_FLG = ' ';
    char DRMCR_FLG = ' ';
    char MATCH_FLG = ' ';
    int LAST_DATE = 0;
    String LAST_TELL = " ";
    String SUP_TEL1 = " ";
    String SUP_TEL2 = " ";
    public HSVR_VAL() {
        for (int i=0;i<20;i++) DATA[i] = new HSVR_DATA();
    }
}
