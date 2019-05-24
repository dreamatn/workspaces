package com.hisun.BP;

public class BPCOFLSR_FLS_DATA {
    public BPCOFLSR_CCYS[] CCYS = new BPCOFLSR_CCYS[20];
    public BPCOFLSR_FLS_DATA() {
        for (int i=0;i<20;i++) CCYS[i] = new BPCOFLSR_CCYS();
    }
}
