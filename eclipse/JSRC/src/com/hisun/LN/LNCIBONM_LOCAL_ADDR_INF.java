package com.hisun.LN;

public class LNCIBONM_LOCAL_ADDR_INF {
    public LNCIBONM_LADDR[] LADDR = new LNCIBONM_LADDR[6];
    public LNCIBONM_LOCAL_ADDR_INF() {
        for (int i=0;i<6;i++) LADDR[i] = new LNCIBONM_LADDR();
    }
}
