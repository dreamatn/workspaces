package com.hisun.BP;

public class BPCSCSWO_DATA_INFO {
    public BPCSCSWO_PVAL_INFO[] PVAL_INFO = new BPCSCSWO_PVAL_INFO[20];
    public BPCSCSWO_DATA_INFO() {
        for (int i=0;i<20;i++) PVAL_INFO[i] = new BPCSCSWO_PVAL_INFO();
    }
}
