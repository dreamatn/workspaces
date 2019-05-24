package com.hisun.BP;

public class BPCUFHSA_OUTPUT_DATA {
    public short REC_NUM = 0;
    public BPCUFHSA_TS_DATA[] TS_DATA = new BPCUFHSA_TS_DATA[366];
    public BPCUFHSA_OUTPUT_DATA() {
        for (int i=0;i<366;i++) TS_DATA[i] = new BPCUFHSA_TS_DATA();
    }
}
