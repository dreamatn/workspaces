package com.hisun.BP;

public class BPCOINVF {
    public int DATE = 0;
    public long JRNNO = 0;
    public int BR = 0;
    public BPCOINVF_BV_INFO[] BV_INFO = new BPCOINVF_BV_INFO[30];
    public BPCOINVF() {
        for (int i=0;i<30;i++) BV_INFO[i] = new BPCOINVF_BV_INFO();
    }
}
