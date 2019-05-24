package com.hisun.BP;

public class BPCOFLSO_FLS_DATA {
    public BPCOFLSO_CCYS[] CCYS = new BPCOFLSO_CCYS[20];
    public BPCOFLSO_FLS_DATA() {
        for (int i=0;i<20;i++) CCYS[i] = new BPCOFLSO_CCYS();
    }
}
