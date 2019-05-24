package com.hisun.BP;

public class BPCSOSTO_CCY_INFO {
    public BPCSOSTO_CCY_DETAIL[] CCY_DETAIL = new BPCSOSTO_CCY_DETAIL[12];
    public BPCSOSTO_CCY_INFO() {
        for (int i=0;i<12;i++) CCY_DETAIL[i] = new BPCSOSTO_CCY_DETAIL();
    }
}
