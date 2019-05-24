package com.hisun.IB;

import com.hisun.BP.BPRINVT_REC_DATA;

public class FILLER1 {
    BPRINVT_REC_DATA[] REC_DATA = new BPRINVT_REC_DATA[200];
    public FILLER1() {
        for (int i=0;i<200;i++) REC_DATA[i] = new BPRINVT_REC_DATA();
    }
}
