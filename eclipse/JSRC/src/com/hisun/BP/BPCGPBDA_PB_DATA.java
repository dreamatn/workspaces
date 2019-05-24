package com.hisun.BP;

public class BPCGPBDA_PB_DATA {
    public short PB_CNT = 0;
    public BPCGPBDA_PB_INFO[] PB_INFO = new BPCGPBDA_PB_INFO[30];
    public BPCGPBDA_PB_DATA() {
        for (int i=0;i<30;i++) PB_INFO[i] = new BPCGPBDA_PB_INFO();
    }
}
