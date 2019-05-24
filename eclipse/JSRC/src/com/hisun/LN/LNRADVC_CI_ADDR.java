package com.hisun.LN;

public class LNRADVC_CI_ADDR {
    public LNRADVC_CI_ADDR_INFO[] CI_ADDR_INFO = new LNRADVC_CI_ADDR_INFO[6];
    public LNRADVC_CI_ADDR() {
        for (int i=0;i<6;i++) CI_ADDR_INFO[i] = new LNRADVC_CI_ADDR_INFO();
    }
}
