package com.hisun.BP;

public class BPCOQAMO {
    public String TO_TLR = " ";
    public int CCY_CNT = 0;
    public BPCOQAMO_CCY_INFO[] CCY_INFO = new BPCOQAMO_CCY_INFO[20];
    public BPCOQAMO() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPCOQAMO_CCY_INFO();
    }
}
