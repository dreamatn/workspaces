package com.hisun.BP;

public class BPCITAXG_OUTPUT {
    public BPCITAXG_TAXR_GROUP[] TAXR_GROUP = new BPCITAXG_TAXR_GROUP[20];
    public BPCITAXG_OUTPUT() {
        for (int i=0;i<20;i++) TAXR_GROUP[i] = new BPCITAXG_TAXR_GROUP();
    }
}
