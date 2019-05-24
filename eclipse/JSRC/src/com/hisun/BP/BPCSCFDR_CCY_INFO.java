package com.hisun.BP;

public class BPCSCFDR_CCY_INFO {
    public BPCSCFDR_CCY_DETAIL[] CCY_DETAIL = new BPCSCFDR_CCY_DETAIL[20];
    public BPCSCFDR_CCY_INFO() {
        for (int i=0;i<20;i++) CCY_DETAIL[i] = new BPCSCFDR_CCY_DETAIL();
    }
}
