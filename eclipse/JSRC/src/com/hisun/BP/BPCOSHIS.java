package com.hisun.BP;

public class BPCOSHIS {
    public BPCOSHIS_REC_TITLE REC_TITLE = new BPCOSHIS_REC_TITLE();
    public BPCOSHIS_QUEUE[] QUEUE = new BPCOSHIS_QUEUE[10];
    public BPCOSHIS() {
        for (int i=0;i<10;i++) QUEUE[i] = new BPCOSHIS_QUEUE();
    }
}
