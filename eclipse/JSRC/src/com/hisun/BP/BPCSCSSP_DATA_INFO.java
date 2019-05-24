package com.hisun.BP;

public class BPCSCSSP_DATA_INFO {
    public BPCSCSSP_PVAL_INFO[] PVAL_INFO = new BPCSCSSP_PVAL_INFO[20];
    public BPCSCSSP_DATA_INFO() {
        for (int i=0;i<20;i++) PVAL_INFO[i] = new BPCSCSSP_PVAL_INFO();
    }
}
