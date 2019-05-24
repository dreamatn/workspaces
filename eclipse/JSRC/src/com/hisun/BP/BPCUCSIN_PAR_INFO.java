package com.hisun.BP;

public class BPCUCSIN_PAR_INFO {
    public BPCUCSIN_PAR_REC[] PAR_REC = new BPCUCSIN_PAR_REC[20];
    public BPCUCSIN_PAR_INFO() {
        for (int i=0;i<20;i++) PAR_REC[i] = new BPCUCSIN_PAR_REC();
    }
}
