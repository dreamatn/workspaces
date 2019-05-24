package com.hisun.BP;

public class BPC1165 {
    public String CPNT_ID = " ";
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public BPC1165_DATA[] DATA = new BPC1165_DATA[20];
    public BPC1165() {
        for (int i=0;i<20;i++) DATA[i] = new BPC1165_DATA();
    }
}
