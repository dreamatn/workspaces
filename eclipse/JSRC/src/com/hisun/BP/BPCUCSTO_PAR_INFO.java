package com.hisun.BP;

public class BPCUCSTO_PAR_INFO {
    public BPCUCSTO_PAR_REC[] PAR_REC = new BPCUCSTO_PAR_REC[20];
    public BPCUCSTO_PAR_INFO() {
        for (int i=0;i<20;i++) PAR_REC[i] = new BPCUCSTO_PAR_REC();
    }
}
