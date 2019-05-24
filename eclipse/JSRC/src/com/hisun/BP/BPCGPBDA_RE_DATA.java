package com.hisun.BP;

public class BPCGPBDA_RE_DATA {
    public short RE_CNT = 0;
    public BPCGPBDA_RE_INFO[] RE_INFO = new BPCGPBDA_RE_INFO[30];
    public BPCGPBDA_RE_DATA() {
        for (int i=0;i<30;i++) RE_INFO[i] = new BPCGPBDA_RE_INFO();
    }
}
