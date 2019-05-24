package com.hisun.BP;

public class BPCSBVBT {
    public BPCSBVBT_RC RC = new BPCSBVBT_RC();
    public String OUTPUT_FMT = " ";
    public BPCSBVBT_BV_DATA[] BV_DATA = new BPCSBVBT_BV_DATA[10];
    public String RCV_TLR = " ";
    public char PSW_TYP = ' ';
    public String TLRC_PSW = " ";
    public String TLRK_PSW = " ";
    public char MOV_FLG = ' ';
    public BPCSBVBT() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCSBVBT_BV_DATA();
    }
}
