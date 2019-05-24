package com.hisun.BP;

public class BPCOSVRO_VAL {
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char AUT_FLG = ' ';
    public char DRMCR_FLG = ' ';
    public char MATCH_FLG = ' ';
    public BPCOSVRO_FEE_INFO[] FEE_INFO = new BPCOSVRO_FEE_INFO[20];
    public short ARRAY_LENGTH = 0;
    public BPCOSVRO_VAL() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCOSVRO_FEE_INFO();
    }
}
