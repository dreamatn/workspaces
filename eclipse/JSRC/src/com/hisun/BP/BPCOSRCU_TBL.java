package com.hisun.BP;

public class BPCOSRCU_TBL {
    public String CCY = " ";
    public BPCOSRCU_RATE_TBL[] RATE_TBL = new BPCOSRCU_RATE_TBL[20];
    public BPCOSRCU_TBL() {
        for (int i=0;i<20;i++) RATE_TBL[i] = new BPCOSRCU_RATE_TBL();
    }
}
