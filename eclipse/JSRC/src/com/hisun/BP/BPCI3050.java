package com.hisun.BP;

public class BPCI3050 {
    public BPCI3050_BV_DATA[] BV_DATA = new BPCI3050_BV_DATA[10];
    public String TLR = " ";
    public char PSW_TYP = ' ';
    public String TLRC_PSW = " ";
    public String TLRK_PSW = " ";
    public char BV_FUNC = ' ';
    public String REP_TLR = " ";
    public BPCI3050() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCI3050_BV_DATA();
    }
}
