package com.hisun.TD;

public class TDCIRULP_SPRD_DATA3 {
    public TDCIRULP_SPRD_PCTS[] SPRD_PCTS = new TDCIRULP_SPRD_PCTS[25];
    public TDCIRULP_SPRD_PNTS[] SPRD_PNTS = new TDCIRULP_SPRD_PNTS[25];
    public TDCIRULP_BASE_RATS[] BASE_RATS = new TDCIRULP_BASE_RATS[25];
    public TDCIRULP_SPRD_DATA3() {
        for (int i=0;i<25;i++) SPRD_PCTS[i] = new TDCIRULP_SPRD_PCTS();
        for (int i=0;i<25;i++) SPRD_PNTS[i] = new TDCIRULP_SPRD_PNTS();
        for (int i=0;i<25;i++) BASE_RATS[i] = new TDCIRULP_BASE_RATS();
    }
}
