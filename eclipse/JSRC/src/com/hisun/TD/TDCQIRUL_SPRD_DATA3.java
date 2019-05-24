package com.hisun.TD;

public class TDCQIRUL_SPRD_DATA3 {
    public TDCQIRUL_SPRD_PCTS[] SPRD_PCTS = new TDCQIRUL_SPRD_PCTS[25];
    public TDCQIRUL_SPRD_PNTS[] SPRD_PNTS = new TDCQIRUL_SPRD_PNTS[25];
    public TDCQIRUL_BASE_RATS[] BASE_RATS = new TDCQIRUL_BASE_RATS[25];
    public TDCQIRUL_SPRD_DATA3() {
        for (int i=0;i<25;i++) SPRD_PCTS[i] = new TDCQIRUL_SPRD_PCTS();
        for (int i=0;i<25;i++) SPRD_PNTS[i] = new TDCQIRUL_SPRD_PNTS();
        for (int i=0;i<25;i++) BASE_RATS[i] = new TDCQIRUL_BASE_RATS();
    }
}
