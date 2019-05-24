package com.hisun.BP;

public class BPCSISTO_CCY_INFO {
    public BPCSISTO_CCY_DETAIL[] CCY_DETAIL = new BPCSISTO_CCY_DETAIL[20];
    public BPCSISTO_CCY_INFO() {
        for (int i=0;i<20;i++) CCY_DETAIL[i] = new BPCSISTO_CCY_DETAIL();
    }
}
