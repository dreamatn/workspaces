package com.hisun.BP;

public class BPCOLHIS {
    public BPCOLHIS_REC_TITLE REC_TITLE = new BPCOLHIS_REC_TITLE();
    public BPCOLHIS_AC_MSG AC_MSG = new BPCOLHIS_AC_MSG();
    public BPCOLHIS_QUEUE[] QUEUE = new BPCOLHIS_QUEUE[10];
    public BPCOLHIS() {
        for (int i=0;i<10;i++) QUEUE[i] = new BPCOLHIS_QUEUE();
    }
}
