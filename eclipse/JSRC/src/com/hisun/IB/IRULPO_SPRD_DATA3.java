package com.hisun.IB;

public class IRULPO_SPRD_DATA3 {
    IRULPO_SPRD_PCTS[] SPRD_PCTS = new IRULPO_SPRD_PCTS[25];
    IRULPO_SPRD_PNTS[] SPRD_PNTS = new IRULPO_SPRD_PNTS[25];
    IRULPO_BASE_RATS[] BASE_RATS = new IRULPO_BASE_RATS[25];
    public IRULPO_SPRD_DATA3() {
        for (int i=0;i<25;i++) SPRD_PCTS[i] = new IRULPO_SPRD_PCTS();
        for (int i=0;i<25;i++) SPRD_PNTS[i] = new IRULPO_SPRD_PNTS();
        for (int i=0;i<25;i++) BASE_RATS[i] = new IRULPO_BASE_RATS();
    }
}
