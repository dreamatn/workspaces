package com.hisun.BP;

public class BPCOFEXP_VAL {
    public String DER_DESC = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public BPCOFEXP_EXP_DATA[] EXP_DATA = new BPCOFEXP_EXP_DATA[50];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCOFEXP_VAL() {
        for (int i=0;i<50;i++) EXP_DATA[i] = new BPCOFEXP_EXP_DATA();
    }
}
