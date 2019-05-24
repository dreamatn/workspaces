package com.hisun.BP;

public class BPCSBVBV {
    public BPCSBVBV_RC RC = new BPCSBVBV_RC();
    public int CNT = 0;
    public BPCSBVBV_BV_DATA[] BV_DATA = new BPCSBVBV_BV_DATA[10];
    public String RCV_TLR = " ";
    public char PSW_TYP = ' ';
    public String TLRC_PSW = " ";
    public String TLRK_PSW = " ";
    public char BV_FUNC = ' ';
    public String PB_NO = " ";
    public String REP_TLR = " ";
    public BPCSBVBV() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCSBVBV_BV_DATA();
    }
}
