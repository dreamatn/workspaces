package com.hisun.BP;

public class BPCSHDCS_CCY_INFO {
    public BPCSHDCS_CCY_DETAIL[] CCY_DETAIL = new BPCSHDCS_CCY_DETAIL[12];
    public BPCSHDCS_CCY_INFO() {
        for (int i=0;i<12;i++) CCY_DETAIL[i] = new BPCSHDCS_CCY_DETAIL();
    }
}
