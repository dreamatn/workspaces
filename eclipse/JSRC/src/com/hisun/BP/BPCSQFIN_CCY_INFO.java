package com.hisun.BP;

public class BPCSQFIN_CCY_INFO {
    public BPCSQFIN_CCY_DETAIL[] CCY_DETAIL = new BPCSQFIN_CCY_DETAIL[20];
    public BPCSQFIN_CCY_INFO() {
        for (int i=0;i<20;i++) CCY_DETAIL[i] = new BPCSQFIN_CCY_DETAIL();
    }
}
