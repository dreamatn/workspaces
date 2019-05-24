package com.hisun.TD;

public class TDCSRMA_SPRD_DATA3 {
    public TDCSRMA_SPRD_PCTS[] SPRD_PCTS = new TDCSRMA_SPRD_PCTS[25];
    public TDCSRMA_SPRD_PNTS[] SPRD_PNTS = new TDCSRMA_SPRD_PNTS[25];
    public TDCSRMA_BASE_RATS[] BASE_RATS = new TDCSRMA_BASE_RATS[25];
    public TDCSRMA_SPRD_DATA3() {
        for (int i=0;i<25;i++) SPRD_PCTS[i] = new TDCSRMA_SPRD_PCTS();
        for (int i=0;i<25;i++) SPRD_PNTS[i] = new TDCSRMA_SPRD_PNTS();
        for (int i=0;i<25;i++) BASE_RATS[i] = new TDCSRMA_BASE_RATS();
    }
}
