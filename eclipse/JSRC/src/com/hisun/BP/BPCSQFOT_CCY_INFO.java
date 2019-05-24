package com.hisun.BP;

public class BPCSQFOT_CCY_INFO {
    public BPCSQFOT_CCY_DETAIL[] CCY_DETAIL = new BPCSQFOT_CCY_DETAIL[12];
    public BPCSQFOT_CCY_INFO() {
        for (int i=0;i<12;i++) CCY_DETAIL[i] = new BPCSQFOT_CCY_DETAIL();
    }
}
