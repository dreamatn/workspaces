package com.hisun.BP;

public class BPCURMOV_CCY_INFO {
    public BPCURMOV_CCY_DETL[] CCY_DETL = new BPCURMOV_CCY_DETL[20];
    public BPCURMOV_CCY_INFO() {
        for (int i=0;i<20;i++) CCY_DETL[i] = new BPCURMOV_CCY_DETL();
    }
}
