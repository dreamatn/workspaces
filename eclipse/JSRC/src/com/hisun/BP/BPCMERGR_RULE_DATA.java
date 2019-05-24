package com.hisun.BP;

public class BPCMERGR_RULE_DATA {
    public BPCMERGR_DATA[] DATA = new BPCMERGR_DATA[20];
    public BPCMERGR_RULE_DATA() {
        for (int i=0;i<20;i++) DATA[i] = new BPCMERGR_DATA();
    }
}
