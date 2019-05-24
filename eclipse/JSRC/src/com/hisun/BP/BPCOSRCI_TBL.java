package com.hisun.BP;

public class BPCOSRCI_TBL {
    public BPCOSRCI_RATE_TBL[] RATE_TBL = new BPCOSRCI_RATE_TBL[20];
    public BPCOSRCI_TBL() {
        for (int i=0;i<20;i++) RATE_TBL[i] = new BPCOSRCI_RATE_TBL();
    }
}
