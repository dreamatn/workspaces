package com.hisun.BP;

public class BPCRFLT_OUTPUT_DATA {
    public BPCRFLT_DATA[] DATA = new BPCRFLT_DATA[10];
    public BPCRFLT_OUTPUT_DATA() {
        for (int i=0;i<10;i++) DATA[i] = new BPCRFLT_DATA();
    }
}
