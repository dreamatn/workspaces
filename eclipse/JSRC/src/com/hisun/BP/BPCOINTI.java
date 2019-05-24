package com.hisun.BP;

public class BPCOINTI {
    public int O_EFF_DT = 0;
    public int O_BR = 0;
    public BPCOINTI_O_TBL[] O_TBL = new BPCOINTI_O_TBL[10];
    public BPCOINTI() {
        for (int i=0;i<10;i++) O_TBL[i] = new BPCOINTI_O_TBL();
    }
}
