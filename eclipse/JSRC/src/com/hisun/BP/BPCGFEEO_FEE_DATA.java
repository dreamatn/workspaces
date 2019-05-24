package com.hisun.BP;

public class BPCGFEEO_FEE_DATA {
    public short FEE_CNT = 0;
    public BPCGFEEO_FEE_INFO[] FEE_INFO = new BPCGFEEO_FEE_INFO[20];
    public char PROC_TYPE = ' ';
    public BPCGFEEO_FEE_DATA() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPCGFEEO_FEE_INFO();
    }
}
