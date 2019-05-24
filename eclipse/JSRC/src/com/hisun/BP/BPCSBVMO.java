package com.hisun.BP;

public class BPCSBVMO {
    public BPCSBVMO_RC RC = new BPCSBVMO_RC();
    public int MOV_DT = 0;
    public long CONF_NO = 0;
    public int IN_BR = 0;
    public String IN_TLR = " ";
    public char BV_STS = ' ';
    public int CNT = 0;
    public BPCSBVMO_BV_DATA[] BV_DATA = new BPCSBVMO_BV_DATA[10];
    public char PB_FLG = ' ';
    public char BR_FLG = ' ';
    public char OUT_TYP = ' ';
    public int APP_NO = 0;
    public char APP_TYPE = ' ';
    public int ONWAY_DT = 0;
    public String VIL_TYP = " ";
    public int CNT1 = 0;
    public BPCSBVMO() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPCSBVMO_BV_DATA();
    }
}
