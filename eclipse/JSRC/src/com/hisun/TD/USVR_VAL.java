package com.hisun.TD;

public class USVR_VAL {
    int EFF_DATE = 0;
    int EXP_DATE = 0;
    char AUT_FLG = ' ';
    char DRMCR_FLG = ' ';
    char MATCH_FLG = ' ';
    USVR_DATA[] DATA = new USVR_DATA[20];
    public USVR_VAL() {
        for (int i=0;i<20;i++) DATA[i] = new USVR_DATA();
    }
}
