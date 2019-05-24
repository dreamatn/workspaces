package com.hisun.BP;

public class BPCOSCBB {
    public int BR = 0;
    public String TLR_CHG = " ";
    public BPCOSCBB_DATA_INFO[] DATA_INFO = new BPCOSCBB_DATA_INFO[10];
    public int CRT_DATE = 0;
    public String TLR = " ";
    public BPCOSCBB() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCOSCBB_DATA_INFO();
    }
}
