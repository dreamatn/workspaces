package com.hisun.BP;

public class BPCOSCTD {
    public int BR = 0;
    public char FUNC = ' ';
    public BPCOSCTD_DATA_INFO[] DATA_INFO = new BPCOSCTD_DATA_INFO[10];
    public int DATE = 0;
    public String TLR = " ";
    public BPCOSCTD() {
        for (int i=0;i<10;i++) DATA_INFO[i] = new BPCOSCTD_DATA_INFO();
    }
}
