package com.hisun.BP;

public class BPCSDRCS_CCY_INFO {
    public BPCSDRCS_CCY_DETAIL[] CCY_DETAIL = new BPCSDRCS_CCY_DETAIL[12];
    public BPCSDRCS_CCY_INFO() {
        for (int i=0;i<12;i++) CCY_DETAIL[i] = new BPCSDRCS_CCY_DETAIL();
    }
}
