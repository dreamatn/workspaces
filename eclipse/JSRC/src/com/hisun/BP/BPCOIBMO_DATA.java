package com.hisun.BP;

public class BPCOIBMO_DATA {
    public BPCOIBMO_CCYS[] CCYS = new BPCOIBMO_CCYS[20];
    public BPCOIBMO_DATA() {
        for (int i=0;i<20;i++) CCYS[i] = new BPCOIBMO_CCYS();
    }
}
