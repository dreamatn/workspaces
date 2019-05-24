package com.hisun.PN;

public class NHSVR_VAL {
    int EFF_DATE = 0;
    int EXP_DATE = 0;
    char AUT_FLG = ' ';
    char DRMCR_FLG = ' ';
    char MATCH_FLG = ' ';
    NHSVR_FEE_INFO[] FEE_INFO = new NHSVR_FEE_INFO[20];
    short ARRAY_LENGTH = 0;
    public NHSVR_VAL() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new NHSVR_FEE_INFO();
    }
}
