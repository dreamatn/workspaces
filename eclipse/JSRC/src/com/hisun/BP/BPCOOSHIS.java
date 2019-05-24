package com.hisun.BP;

import com.hisun.PN.OSHIS_QUEUE;
import com.hisun.PN.OSHIS_REC_TITLE;

public class BPCOOSHIS {
    OSHIS_REC_TITLE REC_TITLE = new OSHIS_REC_TITLE();
    OSHIS_QUEUE[] QUEUE = new OSHIS_QUEUE[10];
    public BPCOOSHIS() {
        for (int i=0;i<10;i++) QUEUE[i] = new OSHIS_QUEUE();
    }
}
