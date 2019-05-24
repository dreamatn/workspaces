package com.hisun.BP;

public class BPCOLIBO_DATA {
    public BPCOLIBO_CCYS[] CCYS = new BPCOLIBO_CCYS[20];
    public BPCOLIBO_DATA() {
        for (int i=0;i<20;i++) CCYS[i] = new BPCOLIBO_CCYS();
    }
}
