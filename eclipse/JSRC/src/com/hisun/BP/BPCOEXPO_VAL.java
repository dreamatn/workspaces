package com.hisun.BP;

public class BPCOEXPO_VAL {
    public String DER_DESC = " ";
    public char FILLER5 = 0X02;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public BPCOEXPO_EXP_DATA[] EXP_DATA = new BPCOEXPO_EXP_DATA[50];
    public short ARRAY_LENGTH = 0;
    public BPCOEXPO_VAL() {
        for (int i=0;i<50;i++) EXP_DATA[i] = new BPCOEXPO_EXP_DATA();
    }
}
