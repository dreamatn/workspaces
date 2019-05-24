package com.hisun.LN;

public class LNCIBONM_ENG_ADDR_INF {
    public LNCIBONM_EADDR[] EADDR = new LNCIBONM_EADDR[6];
    public LNCIBONM_ENG_ADDR_INF() {
        for (int i=0;i<6;i++) EADDR[i] = new LNCIBONM_EADDR();
    }
}
