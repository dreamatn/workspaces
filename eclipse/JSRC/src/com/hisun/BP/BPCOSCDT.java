package com.hisun.BP;

public class BPCOSCDT {
    public int BR = 0;
    public char FUNC = ' ';
    public BPCOSCDT_DATA_INFO[] DATA_INFO = new BPCOSCDT_DATA_INFO[10];
    public int DATE = 0;
    public String TLR = " ";
    public BPCOSCDT() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCOSCDT_DATA_INFO();
    }
}
