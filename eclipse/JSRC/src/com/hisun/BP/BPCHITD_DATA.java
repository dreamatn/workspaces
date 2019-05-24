package com.hisun.BP;

public class BPCHITD_DATA {
    public BPCHITD_REL_ITMS[] REL_ITMS = new BPCHITD_REL_ITMS[32];
    public BPCHITD_DATA() {
        for (int i=0;i<32;i++) REL_ITMS[i] = new BPCHITD_REL_ITMS();
    }
}
