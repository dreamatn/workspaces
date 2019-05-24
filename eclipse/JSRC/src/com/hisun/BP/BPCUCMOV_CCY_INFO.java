package com.hisun.BP;

public class BPCUCMOV_CCY_INFO {
    public BPCUCMOV_CCY_DETL[] CCY_DETL = new BPCUCMOV_CCY_DETL[20];
    public BPCUCMOV_CCY_INFO() {
        for (int i=0;i<20;i++) CCY_DETL[i] = new BPCUCMOV_CCY_DETL();
    }
}
