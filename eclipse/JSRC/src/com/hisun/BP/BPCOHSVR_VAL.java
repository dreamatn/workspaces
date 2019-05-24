package com.hisun.BP;

public class BPCOHSVR_VAL {
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char AUT_FLG = ' ';
    public char DRMCR_FLG = ' ';
    public char MATCH_FLG = ' ';
    public BPCOHSVR_FEE_INFO[] FEE_INFO = new BPCOHSVR_FEE_INFO[20];
    public short ARRAY_LENGTH = 0;
    public BPCOHSVR_VAL() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCOHSVR_FEE_INFO();
    }
}
