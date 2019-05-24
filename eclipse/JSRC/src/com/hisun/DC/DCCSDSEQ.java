package com.hisun.DC;

import com.hisun.SM.SDSEQ_INP_DATA;
import com.hisun.SM.SDSEQ_OUT_DATA;
import com.hisun.SM.SDSEQ_RC;

public class DCCSDSEQ {
    SDSEQ_INP_DATA INP_DATA = new SDSEQ_INP_DATA();
    SDSEQ_OUT_DATA[] OUT_DATA = new SDSEQ_OUT_DATA[10];
    SDSEQ_RC RC = new SDSEQ_RC();
    public DCCSDSEQ() {
        for (int i=0;i<10;i++) OUT_DATA[i] = new SDSEQ_OUT_DATA();
    }
}
