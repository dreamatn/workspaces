package com.hisun.LN;

public class LNRADVS_CI_ADDR {
    public LNRADVS_CI_ADDR_INFO[] CI_ADDR_INFO = new LNRADVS_CI_ADDR_INFO[6];
    public LNRADVS_CI_ADDR() {
        for (int i=0;i<6;i++) CI_ADDR_INFO[i] = new LNRADVS_CI_ADDR_INFO();
    }
}
