package com.hisun.TD;

public class TDCSRMD_SPRD_DATA3 {
    public TDCSRMD_SPRD_PCTS[] SPRD_PCTS = new TDCSRMD_SPRD_PCTS[25];
    public TDCSRMD_SPRD_PNTS[] SPRD_PNTS = new TDCSRMD_SPRD_PNTS[25];
    public TDCSRMD_BASE_RATS[] BASE_RATS = new TDCSRMD_BASE_RATS[25];
    public TDCSRMD_SPRD_DATA3() {
        for (int i=0;i<25;i++) SPRD_PCTS[i] = new TDCSRMD_SPRD_PCTS();
        for (int i=0;i<25;i++) SPRD_PNTS[i] = new TDCSRMD_SPRD_PNTS();
        for (int i=0;i<25;i++) BASE_RATS[i] = new TDCSRMD_BASE_RATS();
    }
}
