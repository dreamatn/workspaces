package com.hisun.BP;

public class BPCSBVVB {
    public String OUTPUT_FMT = " ";
    public short COUNT = 0;
    public BPCSBVVB_DATA[] DATA = new BPCSBVVB_DATA[10];
    public char PSW_TYP = ' ';
    public String PSW = " ";
    public String CPSW = " ";
    public char BV_FUNC = ' ';
    public String PB_NO = " ";
    public String REC_TLR = " ";
    public BPCSBVVB() {
        for (int i=0;i<10;i++) DATA[i] = new BPCSBVVB_DATA();
    }
}
