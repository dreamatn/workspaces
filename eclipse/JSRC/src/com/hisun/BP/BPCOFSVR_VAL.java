package com.hisun.BP;

public class BPCOFSVR_VAL {
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char AUT_FLG = ' ';
    public char DRMCR_FLG = ' ';
    public char MATCH_FLG = ' ';
    public BPCOFSVR_DATA[] DATA = new BPCOFSVR_DATA[20];
    public BPCOFSVR_VAL() {
        for (int i=0;i<20;i++) DATA[i] = new BPCOFSVR_DATA();
    }
}
