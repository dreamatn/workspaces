package com.hisun.TD;

public class TDCSRMM_SPRD_DATA3 {
    public TDCSRMM_SPRD_PCTS[] SPRD_PCTS = new TDCSRMM_SPRD_PCTS[25];
    public TDCSRMM_SPRD_PNTS[] SPRD_PNTS = new TDCSRMM_SPRD_PNTS[25];
    public TDCSRMM_BASE_RATS[] BASE_RATS = new TDCSRMM_BASE_RATS[25];
    public TDCSRMM_SPRD_DATA3() {
        for (int i=0;i<25;i++) SPRD_PCTS[i] = new TDCSRMM_SPRD_PCTS();
        for (int i=0;i<25;i++) SPRD_PNTS[i] = new TDCSRMM_SPRD_PNTS();
        for (int i=0;i<25;i++) BASE_RATS[i] = new TDCSRMM_BASE_RATS();
    }
}
