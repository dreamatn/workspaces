package com.hisun.BP;

public class BPCOFEEL_FEE_DATA {
    public char SEND_FLG = ' ';
    public String EXP_CODE = " ";
    public short FEE_CNT = 0;
    public BPCOFEEL_FEE_INFO[] FEE_INFO = new BPCOFEEL_FEE_INFO[20];
    public BPCOFEEL_FEE_DATA() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCOFEEL_FEE_INFO();
    }
}
