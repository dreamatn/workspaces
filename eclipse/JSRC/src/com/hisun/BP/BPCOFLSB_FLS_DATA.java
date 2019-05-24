package com.hisun.BP;

public class BPCOFLSB_FLS_DATA {
    public BPCOFLSB_CCYS[] CCYS = new BPCOFLSB_CCYS[20];
    public BPCOFLSB_FLS_DATA() {
        for (int i=0;i<20;i++) CCYS[i] = new BPCOFLSB_CCYS();
    }
}
