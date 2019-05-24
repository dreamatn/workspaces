package com.hisun.BP;

public class BPCI3030 {
    public int MOV_DT = 0;
    public long CONF_NO = 0;
    public int BR = 0;
    public String TLR = " ";
    public char BV_STS = ' ';
    public BPCI3030_BV_DATA[] BV_DATA = new BPCI3030_BV_DATA[10];
    public String ACNO = " ";
    public String APP_NO_G = " ";
    public BPCI3030() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCI3030_BV_DATA();
    }
}
