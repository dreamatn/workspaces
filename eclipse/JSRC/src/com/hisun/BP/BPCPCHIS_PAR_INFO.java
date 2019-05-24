package com.hisun.BP;

public class BPCPCHIS_PAR_INFO {
    public BPCPCHIS_PAR_REC[] PAR_REC = new BPCPCHIS_PAR_REC[20];
    public BPCPCHIS_PAR_INFO() {
        for (int i=0;i<20;i++) PAR_REC[i] = new BPCPCHIS_PAR_REC();
    }
}
