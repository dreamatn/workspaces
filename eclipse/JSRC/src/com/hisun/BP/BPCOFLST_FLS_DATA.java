package com.hisun.BP;

public class BPCOFLST_FLS_DATA {
    public BPCOFLST_CCYS[] CCYS = new BPCOFLST_CCYS[20];
    public BPCOFLST_FLS_DATA() {
        for (int i=0;i<20;i++) CCYS[i] = new BPCOFLST_CCYS();
    }
}
