package com.hisun.BP;

public class BPCO151 {
    public int MOV_DT = 0;
    public long CONF_NO = 0;
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public char BV_STS = ' ';
    public int CNT = 0;
    public BPCO151_BV_DATA[] BV_DATA = new BPCO151_BV_DATA[10];
    public int APP_NO = 0;
    public char FLG = ' ';
    public BPCO151() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCO151_BV_DATA();
    }
}
