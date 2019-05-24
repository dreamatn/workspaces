package com.hisun.BP;

public class BPCQFLT_OUTPUT_DATA {
    public short FLT_CNT = 0;
    public BPCQFLT_DATA[] DATA = new BPCQFLT_DATA[100];
    public BPCQFLT_OUTPUT_DATA() {
        for (int i=0;i<100;i++) DATA[i] = new BPCQFLT_DATA();
    }
}
