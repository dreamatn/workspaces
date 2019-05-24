package com.hisun.IB;

import com.hisun.BP.BPRINVT_REC_DATA1;

public class FILLER2 {
    BPRINVT_REC_DATA1[] REC_DATA1 = new BPRINVT_REC_DATA1[200];
    public FILLER2() {
        for (int i=0;i<200;i++) REC_DATA1[i] = new BPRINVT_REC_DATA1();
    }
}
