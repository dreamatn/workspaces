package com.hisun.BP;

public class BPVFSVR_VAL {
    public BPVFSVR_DATA[] DATA = new BPVFSVR_DATA[20];
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char AUT_FLG = ' ';
    public char DRMCR_FLG = ' ';
    public char MATCH_FLG = ' ';
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPVFSVR_VAL() {
        for (int i=0;i<20;i++) DATA[i] = new BPVFSVR_DATA();
    }
}
