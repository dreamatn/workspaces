package com.hisun.BP;

public class BPCSCFHD_CCY_INFO {
    public BPCSCFHD_CCY_DETAIL[] CCY_DETAIL = new BPCSCFHD_CCY_DETAIL[20];
    public BPCSCFHD_CCY_INFO() {
        for (int i=0;i<20;i++) CCY_DETAIL[i] = new BPCSCFHD_CCY_DETAIL();
    }
}
